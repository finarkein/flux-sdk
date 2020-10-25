package io.finarkein.flux.fiber.hiu;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.finarkein.auth.oauth2.FinarkeinCredentials;
import io.finarkein.flux.fiber.hiu.api.HiuApi;
import io.finarkein.flux.fiber.hiu.api.RequestInterceptor;
import io.finarkein.flux.fiber.hiu.model.ConsentRequest;
import io.finarkein.flux.fiber.hiu.model.ConsentStatus;
import io.finarkein.flux.fiber.hiu.model.ConsentToken;
import io.finarkein.flux.fiber.hiu.model.DataFetchId;
import io.finarkein.flux.fiber.hiu.model.DataFetchRequest;
import io.finarkein.flux.fiber.hiu.model.DataRequestToken;
import io.finarkein.flux.fiber.hiu.model.FetchResponse;
import io.finarkein.flux.fiber.hiu.model.FetchStatus;
import io.finarkein.flux.fiber.hiu.model.StatusResponse;
import io.finarkein.flux.fiber.hiu.model.common.Error;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Objects;

/**
 * Flux fiber for Health Information User.
 */
public class HiuFiber {

    private final HiuApi api;
    private final String cmId;

    public HiuFiber() throws IOException {
        Config config = ConfigFactory.load();
        String baseUrl = "https://api.finarkein.com";
        String hiuSuffix = "/health/hiu/";
        if (config.hasPath("finarkein.api.baseUrl"))
            baseUrl = config.getString("finarkein.api.baseUrl");
        if (config.hasPath("finarkein.api.hiu.suffix"))
            hiuSuffix = config.getString("finarkein.api.hiu.suffix");

        String url = baseUrl + hiuSuffix;
        // Add 'Authorization' header in API calls
        FinarkeinCredentials credentials = FinarkeinCredentials.get();
        OkHttpClient http = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor(credentials))
                .build();
        this.cmId = "sbx";
        Retrofit retrofit = new Retrofit.Builder()
                .client(http)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        api = retrofit.create(HiuApi.class);
    }

    /**
     * Creates a consent request to get data about a patient by HIU user.
     *
     * @param consentInitRequiredData A ConsentInitRequest object that has the minimum data required for initializing content.
     * @return A ConsentToken required for DataFetchToken.
     * @throws IOException
     * @see ConsentRequest
     * @see io.finarkein.flux.fiber.hiu.model.ConsentToken
     */
    public ConsentToken createConsent(ConsentRequest consentInitRequiredData) throws IOException {
        return api.consentInit(cmId, consentInitRequiredData).execute().body();
    }

    /**
     * Checks the status of the consent and returns consent artefact if consent if granted, else error.
     *
     * @param consentToken       A ConsentToken that has consentRequestId required to check consent status.
     * @param noOfStatusRequests A integer representing number of times the consent status is called.
     * @return A DataFetchToken required for requesting data.
     * @see DataRequestToken
     * @see ConsentToken
     * @see StatusResponse
     * @see ConsentStatus
     * @see Error
     */
    @SneakyThrows
    public DataRequestToken getDataFetchToken(ConsentToken consentToken, int noOfStatusRequests) {
        // todo add noOfStatusRequests validity

        //calls getConsentStatus after certain period and returns the artefactId to client after consent granted
        //returns error code if the consent is not granted for long time

        StatusResponse status = getConsentStatus(consentToken);
        while (noOfStatusRequests-- > 0) {
            if (!Objects.equals(ConsentStatus.GRANTED.toString(), status.getStatus())) {
                Thread.sleep(1000 * 10L);
                status = getConsentStatus(consentToken);
            } else break;
        }

        if (Objects.equals(ConsentStatus.GRANTED.toString(), status.getStatus())) {
            return DataRequestToken.builder()
                    .consentRequestId(status.getConsentRequestId())
                    .build();
        }

        return DataRequestToken.builder()
                .error(Error.builder()
                        .message("Consent status: " + status.getStatus())
                        .code(4000) // todo set different codes for all status states
                        .build())
                .build();
    }

    /**
     * Get status of consent request done previously.
     *
     * @param consentToken A ConsentToken required to check status of the consent.
     * @return A ConsentStatusResponse that has information regarding the consent.
     * @throws IOException
     * @see StatusResponse
     * @see ConsentToken
     */
    public StatusResponse getConsentStatus(ConsentToken consentToken) throws IOException {
        //calls hiuServer to check consentStatus
        Call<StatusResponse> consentStatusCall = api.consentStatus(cmId, consentToken);
        StatusResponse consentStatus = consentStatusCall.execute().body();
        if (consentStatus != null && consentToken.getConsentRequestId() != null)
            consentToken.setConsentRequestId(consentStatus.getConsentRequestId());
        return consentStatus;
    }

    /**
     * Request for Health information against a consent id.
     *
     * @param dataFetchRequest A DataFetchRequest required for requesting the data.
     * @return A DataFetchId that contains fetchId and transactionId.
     * @throws IOException
     * @see DataFetchId
     * @see DataFetchRequest
     */
    public DataFetchId requestData(DataFetchRequest dataFetchRequest) throws IOException {
        Call<DataFetchId> requestDataCall = api.requestData(cmId, dataFetchRequest);
        return requestDataCall.execute().body();
    }

    /**
     * Checks if the data has been received and returns the data if received else, error.
     *
     * @param dataFetchId A DataFetchId representing the data request information.
     * @return A FetchResponse that contains the data block.
     * @throws IOException
     * @throws IllegalStateException
     * @see FetchResponse
     * @see DataFetchId
     * @see FetchStatus
     */
    @SneakyThrows
    public FetchResponse fetchData(DataFetchId dataFetchId) throws IOException {
        FetchResponse fetchResponse = executeAndGetFetchResponse(dataFetchId);
        if (fetchResponse == null) {
            throw new IllegalStateException("Failed to get response from server");
        }
        if (FetchStatus.TRANSFERRED.equals(fetchResponse.getFetchStatus()))
            return fetchResponse;
        int i = 5;
        while (i-- > 0) {
            Thread.sleep(1000 * 5L);
            fetchResponse = executeAndGetFetchResponse(dataFetchId);
            if (fetchResponse == null) {
                throw new IllegalStateException("Failed to get response from server");
            }
            if (FetchStatus.TRANSFERRED.equals(fetchResponse.getFetchStatus()))
                return fetchResponse;
        }
        return fetchResponse;
    }

    /**
     * Returns the data.
     *
     * @param dataFetchId A DataFetchId representing the data request information.
     * @return A FetchResponse that contains the data block.
     * @throws IOException
     * @see FetchResponse
     * @see DataFetchId
     */
    private FetchResponse executeAndGetFetchResponse(DataFetchId dataFetchId) throws IOException {
        Call<FetchResponse> fetchDataCall = api.fetchData(dataFetchId);
        return fetchDataCall.execute().body();
    }

}
