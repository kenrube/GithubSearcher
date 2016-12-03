package org.odddev.githubsearcher.core.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author kenrube
 * @since 03.12.16
 */

class ResponseInterceptor implements Interceptor {

    private static final int UNPROCESSABLE_ENTITY_ERROR_CODE = 422;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() == UNPROCESSABLE_ENTITY_ERROR_CODE) {
            throw new UnprocessableEntityException();
        }
        return response;
    }
}
