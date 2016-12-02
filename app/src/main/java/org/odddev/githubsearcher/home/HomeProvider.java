package org.odddev.githubsearcher.home;

import android.text.TextUtils;

import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.network.IServerApi;
import org.odddev.githubsearcher.core.rx.ISchedulersResolver;
import org.odddev.githubsearcher.home.repo.Repo;
import org.odddev.githubsearcher.home.repo.ReposResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Single;

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
    public Single<List<Repo>> getRepos(String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            return Single.just(new ArrayList<>());
        } else {
            return serverApi.getRepos(keyword)
                    .map(ReposResponse::getRepos)
                    .compose(schedulersResolver.applyDefaultSchedulers());
        }
    }
}
