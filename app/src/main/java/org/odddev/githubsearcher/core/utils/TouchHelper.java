package org.odddev.githubsearcher.core.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class TouchHelper extends ItemTouchHelper.SimpleCallback {

    private Callback callback;

    public TouchHelper(Callback callback) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);

        this.callback = callback;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        callback.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        callback.onSwiped(viewHolder.getAdapterPosition());
    }

    public interface Callback {

        void onMove(int fromPosition, int toPosition);

        void onSwiped(int position);
    }
}
