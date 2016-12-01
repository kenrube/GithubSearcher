package org.odddev.githubsearcher.core.rx;

import rx.Observable;
import rx.Scheduler;

/**
 * @author kenrube
 * @since 02.12.16
 */

public interface ISchedulersResolver {

    Scheduler ioScheduler();

    Scheduler mainThreadScheduler();

    <T> Observable.Transformer<T, T> applyDefaultSchedulers();
}
