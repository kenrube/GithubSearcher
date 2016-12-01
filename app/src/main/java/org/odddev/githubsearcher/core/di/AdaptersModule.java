package org.odddev.githubsearcher.core.di;

import android.support.annotation.NonNull;

import org.odddev.githubsearcher.home.repo.ReposAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author kenrube
 * @since 02.12.16
 */

@Module
public class AdaptersModule {

    @Provides @NonNull @Singleton
    ReposAdapter provideReposAdapter() {
        return new ReposAdapter();
    }
}
