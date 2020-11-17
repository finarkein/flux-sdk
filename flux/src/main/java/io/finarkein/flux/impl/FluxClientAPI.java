package io.finarkein.flux.impl;

import io.finarkein.flux.BytesWrapper;
import io.finarkein.flux.StorageRef;
import io.finarkein.flux.UploadResponse;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;

public interface FluxClientAPI {

	@GET("pipeline/hello")
	Call<FileResponse> hello();

	@POST("filestore/upload")
	@Multipart
	Call<UploadResponse> uploadFile(@Part MultipartBody.Part filePart);

	@Streaming
	@GET("filestore/isPresent")
	Call<ResponseBody> downloadFile(@Body StorageRef storageRef, String toDir);

	@GET("filestore/isPresent")
	Call<Boolean> isFilePresent(@Body StorageRef storageRef);

	@DELETE("filestore/delete")
	Call<Boolean> deleteFile(@Body StorageRef storageRef);

	@POST("pipeline/run")
	Call<BytesWrapper> pipelineRun(@Body BytesWrapper pipeline);

}
