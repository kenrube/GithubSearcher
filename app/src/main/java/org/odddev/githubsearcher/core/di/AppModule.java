package org.odddev.githubsearcher.core.di;

import android.content.Context;
import android.support.annotation.NonNull;

import org.odddev.githubsearcher.core.eventBus.RxEventBus;
import org.odddev.githubsearcher.core.rx.ISchedulersResolver;
import org.odddev.githubsearcher.core.rx.SchedulersResolver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author kenrube
 * @since 01.12.16
 */

@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides @NonNull @Singleton
    Context provideContext() {
        return context;
    }

    @Provides @NonNull @Singleton
    ISchedulersResolver provideSchedulersResolver() {
        return new SchedulersResolver();
    }

    @Provides @NonNull @Singleton
    RxEventBus provideEventBus() {
        return new RxEventBus();
    }
}
