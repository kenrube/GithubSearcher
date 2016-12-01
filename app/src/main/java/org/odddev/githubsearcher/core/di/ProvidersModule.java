package org.odddev.githubsearcher.core.di;

import android.support.annotation.NonNull;

import org.odddev.githubsearcher.home.HomeProvider;
import org.odddev.githubsearcher.home.IHomeProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author kenrube
 * @since 01.12.16
 */

@Module
class ProvidersModule {

    @Provides @NonNull @Singleton
    IHomeProvider provideHomeProvider() {
        return new HomeProvider();
    }
}
