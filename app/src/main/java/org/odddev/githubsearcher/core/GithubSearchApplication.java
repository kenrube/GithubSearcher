package org.odddev.githubsearcher.core;

import android.app.Application;

import org.odddev.githubsearcher.BuildConfig;
import org.odddev.githubsearcher.core.di.AppModule;
import org.odddev.githubsearcher.core.di.DaggerAppComponent;
import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.network.NetworkModule;

import timber.log.Timber;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class GithubSearchApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initTimber();
        initDagger();
    }

    private void initTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void initDagger() {
        Injector.init(DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build());
    }
}
