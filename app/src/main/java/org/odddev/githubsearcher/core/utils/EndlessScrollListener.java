package org.odddev.githubsearcher.core.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author kenrube
 * @since 04.12.16
 */

public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 5;
    private static int PAGE = 1;

    private int previousTotal = 0;
    private boolean loading = true;

    private LinearLayoutManager layoutManager;
    private Callback callback;

    public EndlessScrollListener(LinearLayoutManager layoutManager, Callback callback) {
        this.layoutManager = layoutManager;
        this.callback = callback;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        } else if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            PAGE++;
            callback.onLoadMore(PAGE);
            loading = true;
        }
    }

    public interface Callback {

        void onLoadMore(int page);
    }
}
