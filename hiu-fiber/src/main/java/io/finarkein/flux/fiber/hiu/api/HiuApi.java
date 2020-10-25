package io.finarkein.flux.fiber.hiu.api;

import io.finarkein.flux.fiber.hiu.model.ConsentRequest;
import io.finarkein.flux.fiber.hiu.model.ConsentToken;
import io.finarkein.flux.fiber.hiu.model.DataFetchId;
import io.finarkein.flux.fiber.hiu.model.DataFetchRequest;
import io.finarkein.flux.fiber.hiu.model.FetchResponse;
import io.finarkein.flux.fiber.hiu.model.StatusResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface HiuApi {

    @POST("consent/init")
    Call<ConsentToken> consentInit(@Header("X-CM-ID") String xcmid,
                                   @Body ConsentRequest consentRequest);

    @POST("consent/status")
    Call<StatusResponse> consentStatus(@Header("X-CM-ID") String xcmid,
                                       @Body ConsentToken consentToken);

    @POST("data/request")
    Call<DataFetchId> requestData(@Header("X-CM-ID") String xcmid,
                                  @Body DataFetchRequest dataFetchRequest);

    @POST("data/fetch")
    Call<FetchResponse> fetchData(@Body DataFetchId dataFetchId);

}
