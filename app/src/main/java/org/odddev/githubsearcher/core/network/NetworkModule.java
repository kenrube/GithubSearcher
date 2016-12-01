package org.odddev.githubsearcher.core.network;

import android.support.annotation.NonNull;

import org.odddev.githubsearcher.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * @author kenrube
 * @since 01.12.16
 */

@Module
public class NetworkModule {

    private static final String HTTP_LOG_TAG = "OkHttp";

    @NonNull
    private final String baseUrl;

    public NetworkModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides @NonNull @Singleton
    String provideBaseUrl() {
        return baseUrl;
    }

    @Provides @NonNull @Singleton
    OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Timber.tag(HTTP_LOG_TAG).d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        return okHttpBuilder.build();
    }

    @Provides @NonNull @Singleton
    IServerApi provideServerApi(@NonNull OkHttpClient okHttpClient, @NonNull String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG)
                .build()
                .create(IServerApi.class);
    }
}
