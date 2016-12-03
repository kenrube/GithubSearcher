package org.odddev.githubsearcher.core.di;

import org.odddev.githubsearcher.core.network.ConnectReceiver;
import org.odddev.githubsearcher.core.network.HeaderInterceptor;
import org.odddev.githubsearcher.core.network.NetworkModule;
import org.odddev.githubsearcher.home.HomeActivity;
import org.odddev.githubsearcher.home.HomePresenter;
import org.odddev.githubsearcher.home.HomeProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author kenrube
 * @since 01.12.16
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ProvidersModule.class,
        PresentersModule.class,
        NetworkModule.class,
        AdaptersModule.class
})
public interface AppComponent {

    void inject(HomeActivity homeActivity);

    void inject(HomePresenter homePresenter);

    void inject(HomeProvider homeProvider);

    void inject(ConnectReceiver connectReceiver);

    void inject(HeaderInterceptor headerInterceptor);
}
