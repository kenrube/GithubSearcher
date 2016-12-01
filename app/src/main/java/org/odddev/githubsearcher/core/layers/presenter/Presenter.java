package org.odddev.githubsearcher.core.layers.presenter;

import android.support.annotation.NonNull;

import org.odddev.githubsearcher.core.layers.view.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kenrube
 * @since 02.12.16
 */

public abstract class Presenter<View extends IView> {

    private List<View> views = new ArrayList<>();

    public void attachView(@NonNull View view) {
        views.add(view);
        onViewAttached(view);
    }

    public void detachView(@NonNull View view) {
        onViewDetached(view);
        views.remove(view);
    }

    protected List<View> getViews() {
        return views;
    }

    protected void onViewAttached(@NonNull View view) {
    }

    protected void onViewDetached(@NonNull View view) {
    }
}
