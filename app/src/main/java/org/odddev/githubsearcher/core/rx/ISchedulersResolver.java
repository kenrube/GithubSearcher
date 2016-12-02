package org.odddev.githubsearcher.core.rx;

import rx.Scheduler;
import rx.Single;

/**
 * @author kenrube
 * @since 02.12.16
 */

public interface ISchedulersResolver {

    Scheduler ioScheduler();

    Scheduler mainThreadScheduler();

    <T> Single.Transformer<T, T> applyDefaultSchedulers();
}
