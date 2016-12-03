package org.odddev.githubsearcher.home;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import org.odddev.githubsearcher.core.di.Injector;
import org.odddev.githubsearcher.core.eventBus.RxEventBus;
import org.odddev.githubsearcher.core.layers.presenter.Presenter;
import org.odddev.githubsearcher.core.network.ConnectedEvent;
import org.odddev.githubsearcher.core.network.UnprocessableEntityException;
import org.odddev.githubsearcher.home.repo.Repo;

import java.net.UnknownHostException;
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

    @Inject
    RxEventBus eventBus;

    public HomePresenter() {
        Injector.getAppComponent().inject(this);

        eventBus.subscribe(ConnectedEvent.class, event -> showConnected());
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

        cancelPreviousRequest();

        getReposSubscription = provider.getRepos(keyword)
                .subscribe(
                        repos -> {
                            this.repos = repos;
                            showRepos(repos);
                        },
                        throwable -> {
                            Timber.e(throwable, throwable.getLocalizedMessage());
                            if (throwable instanceof UnknownHostException) {
                                showConnectionError();
                            } else if (throwable instanceof UnprocessableEntityException) {
                                showIncorrectInputError();
                            } else {
                                showError(throwable.getLocalizedMessage());
                            }
                            compositeSubscription.remove(getReposSubscription);
                        });
        compositeSubscription.add(getReposSubscription);
    }

    private void cancelPreviousRequest() {
        if (getReposSubscription != null && !getReposSubscription.isUnsubscribed()) {
            getReposSubscription.unsubscribe();
        }
    }

    private void showRepos(List<Repo> repos) {
        for (IHomeView view : getViews()) {
            view.showRepos(repos);
        }
    }

    private void showConnectionError() {
        for (IHomeView view : getViews()) {
            view.showConnectionError();
        }
    }

    private void showConnected() {
        for (IHomeView view : getViews()) {
            view.showConnected();
        }
    }

    private void showIncorrectInputError() {
        for (IHomeView view : getViews()) {
            view.showIncorrectInputError();
        }
    }

    private void showError(String error) {
        for (IHomeView view : getViews()) {
            view.showError(error);
        }
    }

    @Override
    protected void onDestroy() {
        compositeSubscription.unsubscribe();
    }
}
