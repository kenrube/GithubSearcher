package org.odddev.githubsearcher.core.network;

import android.content.Context;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.core.di.Injector;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author kenrube
 * @since 04.12.16
 */

public class HeaderInterceptor implements Interceptor {

    @Inject
    Context context;

    public HeaderInterceptor() {
        Injector.getAppComponent().inject(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder()
                .header("User-Agent", context.getString(R.string.app_name))
                .method(original.method(), original.body());
        return chain.proceed(builder.build());
    }
}
