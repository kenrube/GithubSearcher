package org.odddev.githubsearcher.core.eventBus;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import timber.log.Timber;

/**
 * @author kenrube
 * @since 03.12.16
 */

public class RxEventBus {

    private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public void send(Object o) {
        bus.onNext(o);
    }

    public <T> Subscription subscribe(Class<T> tClass, Action1<? super T> onNext) {
        return getEventObservable(tClass)
                .subscribe(
                        onNext,
                        throwable -> Timber.e(throwable, throwable.getLocalizedMessage()));
    }

    private <T> Observable<T> getEventObservable(Class<T> eventClass) {
        return bus.compose(filter(eventClass));
    }

    private static <T> Observable.Transformer<Object, T> filter(final Class<T> tClass) {
        return observable -> observable.filter(o -> o.getClass().equals(tClass)).cast(tClass);
    }
}
