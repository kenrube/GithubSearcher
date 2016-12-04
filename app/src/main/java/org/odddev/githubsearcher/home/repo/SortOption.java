package org.odddev.githubsearcher.home.repo;

import android.support.annotation.IntDef;

/**
 * @author kenrube
 * @since 04.12.16
 */

@IntDef({
        SortOption.DEFAULT,
        SortOption.SIZE,
        SortOption.FORKS,
        SortOption.STARS
})
public @interface SortOption {

    int DEFAULT = 0;
    int SIZE = 1;
    int FORKS = 2;
    int STARS = 3;
}
