package org.odddev.githubsearcher.core.utils;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * @author kenrube
 * @since 03.12.16
 */

public abstract class DiffCallback<T> extends DiffUtil.Callback {

    protected final List<T> oldList;
    protected final List<T> newList;

    public DiffCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }
}
