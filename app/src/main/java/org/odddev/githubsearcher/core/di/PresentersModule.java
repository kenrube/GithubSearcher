package org.odddev.githubsearcher.core.di;

import android.support.annotation.NonNull;

import org.odddev.githubsearcher.home.HomePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * @author kenrube
 * @since 01.12.16
 */

@Module
class PresentersModule {

    @Provides @NonNull
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

    @Provides @NonNull @Singleton
    HomePresenter provideHomePresenter() {
        return new HomePresenter();
    }
}
