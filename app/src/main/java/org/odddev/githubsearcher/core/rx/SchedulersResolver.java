package org.odddev.githubsearcher.core.rx;

import rx.Scheduler;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class SchedulersResolver implements ISchedulersResolver {

    @Override
    public Scheduler ioScheduler() {
        return Schedulers.io();
    }

    @Override
    public Scheduler mainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public <T> Single.Transformer<T, T> applyDefaultSchedulers() {
        return single -> single
                .subscribeOn(ioScheduler())
                .observeOn(mainThreadScheduler());
    }
}
