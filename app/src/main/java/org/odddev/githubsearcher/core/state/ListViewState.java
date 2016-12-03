package org.odddev.githubsearcher.core.state;

import android.support.annotation.IntDef;

/**
 * @author kenrube
 * @since 03.12.16
 */

@IntDef({
        ListViewState.EMPTY,
        ListViewState.LOADING,
        ListViewState.FILLED,
        ListViewState.ERROR
})
public @interface ListViewState {

    int EMPTY = 0;
    int LOADING = 1;
    int FILLED = 2;
    int ERROR = 3;
}
