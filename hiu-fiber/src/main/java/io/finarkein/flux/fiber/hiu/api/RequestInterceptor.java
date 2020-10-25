package io.finarkein.flux.fiber.hiu.api;

import io.finarkein.auth.oauth2.FinarkeinCredentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Intercepts outgoing requests to ensure required metadata
 * is applied.
 */
public class RequestInterceptor implements Interceptor {

    private final FinarkeinCredentials credentials;

    public RequestInterceptor(FinarkeinCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (Objects.nonNull(credentials)) {
            Map<String, List<String>> metadata = credentials.getRequestMetadata();
            metadata.forEach((header, values) ->
                    values.stream().findFirst()
                            .map(value -> builder.header(header, value))
            );
        }
        return chain.proceed(builder.build());
    }
}
