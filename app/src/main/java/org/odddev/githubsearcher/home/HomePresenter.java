package org.odddev.githubsearcher.home;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.layers.presenter.Presenter;
import org.odddev.githubsearcher.home.repo.Repo;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class HomePresenter extends Presenter<IHomeView> {

    private List<Repo> repos;
    private String keyword;

    private Subscription getReposSubscription;

    @Inject
    IHomeProvider provider;

    @Inject
    CompositeSubscription compositeSubscription;

    public HomePresenter() {
        Injector.getAppComponent().inject(this);

        keyword = "Tetris";
    }

    @Override
    protected void onViewAttached(@NonNull IHomeView view) {
        super.onViewAttached(view);

        if (repos != null) {
            showRepos(repos);
        } else if (!TextUtils.isEmpty(keyword)) {
            getRepos(keyword);
        }
    }

    void getRepos(String keyword) {
        this.keyword = keyword;

        getReposSubscription = provider.getRepos(keyword)
                .subscribe(
                        repos -> {
                            this.repos = repos;
                            showRepos(repos);
                        },
                        throwable -> {
                            Timber.e(throwable, throwable.getLocalizedMessage());
                            showError(throwable.getLocalizedMessage());
                            compositeSubscription.remove(getReposSubscription);
                        },
                        () -> compositeSubscription.remove(getReposSubscription));
        compositeSubscription.add(getReposSubscription);
    }

    private void showRepos(List<Repo> repos) {
        for (IHomeView view : getViews()) {
            view.showRepos(repos);
        }
    }

    private void showError(String error) {
        for (IHomeView view : getViews()) {
            view.showError(error);
        }
    }
}
