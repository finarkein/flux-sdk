package io.finarkein.flux.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.finarkein.auth.oauth2.FinarkeinCredentials;
import io.finarkein.flux.BytesWrapper;
import io.finarkein.flux.StorageRef;
import io.finarkein.flux.UploadResponse;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static io.finarkein.flux.Functions.fromBytesWrapper;

class FluxClient {

	private final FluxClientAPI api;
	private final Gson gson;

	public FluxClient() throws IOException {
		Config config = ConfigFactory.load();
		String baseUrl = config.getString("finarkein.api.baseUrl");
		String hiuSuffix = config.getString("finarkein.api.flux.suffix");
		String url = baseUrl + hiuSuffix;

		final boolean credentialEnabled = config.getBoolean("finarkein.api.credential.enabled");
		final OkHttpClient.Builder builder = new OkHttpClient.Builder();
		if (credentialEnabled)
			builder.addInterceptor(new RequestInterceptor(FinarkeinCredentials.get()));
		OkHttpClient http = builder.connectTimeout(100, TimeUnit.SECONDS)
				.readTimeout(900, TimeUnit.SECONDS).build();

		gson = new GsonBuilder()
				.setLenient()
				.create();

		Retrofit retrofit = new Retrofit.Builder()
				.client(http)
				.baseUrl(url)
				.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		api = retrofit.create(FluxClientAPI.class);
	}

	public UploadResponse uploadFile(String fileToUpload) throws IOException {
		Objects.requireNonNull(fileToUpload, "fileToUpload cannot be null");

		File file = new File(fileToUpload);

		MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(),
				RequestBody.create(MediaType.parse("image/*"), file));

		Call<UploadResponse> call = api.uploadFile(filePart);
		return call.execute().body();
	}

	public byte[] pipelineRun(byte[] pipeline) throws IOException {
		final Call<BytesWrapper> pipelineOutput = api.pipelineRun(new BytesWrapper(pipeline));
		final Response<BytesWrapper> response = pipelineOutput.execute();
		if (response.isSuccessful())
			return response.body().getBytes();

		final BytesWrapper bytesWrapper = gson.fromJson(response.errorBody().string(), BytesWrapper.class);
		throw new RuntimeException("Error while running pipeline:" + fromBytesWrapper.apply(bytesWrapper));
	}

	public boolean isFilePresent(StorageRef storageRef) {
		try {
			return api.isFilePresent(storageRef).execute().body();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean deleteFile(StorageRef storageRef) {
		try {
			return api.deleteFile(storageRef).execute().body();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
