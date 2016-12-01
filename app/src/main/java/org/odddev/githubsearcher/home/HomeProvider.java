package org.odddev.githubsearcher.home;

import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.network.IServerApi;
import org.odddev.githubsearcher.core.rx.ISchedulersResolver;
import org.odddev.githubsearcher.home.repo.Repo;
import org.odddev.githubsearcher.home.repo.ReposResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class HomeProvider implements IHomeProvider {

    @Inject
    IServerApi serverApi;

    @Inject
    ISchedulersResolver schedulersResolver;

    public HomeProvider() {
        Injector.getAppComponent().inject(this);
    }

    @Override
    public Observable<List<Repo>> getRepos(String keyword) {
        return serverApi.getRepos(keyword)
                .map(ReposResponse::getRepos)
                .compose(schedulersResolver.applyDefaultSchedulers());
    }
}
