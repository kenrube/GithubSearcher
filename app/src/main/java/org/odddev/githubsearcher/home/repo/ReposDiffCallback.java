package org.odddev.githubsearcher.home.repo;

import org.odddev.githubsearcher.core.utils.DiffCallback;

import java.util.List;

/**
 * @author kenrube
 * @since 03.12.16
 */

public class ReposDiffCallback extends DiffCallback<Repo> {

    public ReposDiffCallback(List<Repo> oldList, List<Repo> newList) {
        super(oldList, newList);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }
}
