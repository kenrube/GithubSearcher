package org.odddev.githubsearcher.home.repo;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.databinding.RepoItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kenrube
 * @since 02.12.16
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder> {

    private List<Repo> repos;
    private @SortOption int sortOption;

    public ReposAdapter() {
        repos = new ArrayList<>();
        sortOption = SortOption.DEFAULT;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RepoViewHolder(DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.binding.setRepo(repos.get(position));
        holder.binding.setSortOption(sortOption);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setSortOption(@SortOption int sortOption) {
        this.sortOption = sortOption;
        switch (this.sortOption) {
            case SortOption.SIZE: {
                Collections.sort(repos,
                        (lhs, rhs) -> rhs.getSize().compareTo(lhs.getSize()));
                notifyDataSetChanged();
                break;
            }
            case SortOption.FORKS: {
                Collections.sort(repos,
                        (lhs, rhs) -> rhs.getForksCount().compareTo(lhs.getForksCount()));
                notifyDataSetChanged();
                break;
            }
            case SortOption.STARS: {
                Collections.sort(repos,
                        (lhs, rhs) -> rhs.getStargazersCount().compareTo(lhs.getStargazersCount()));
                notifyDataSetChanged();
                break;
            }
            case SortOption.DEFAULT:
            default: {
                break;
            }
        }
    }

    public void sortRepos() {
        setSortOption(sortOption);
    }

    public void setRepos(List<Repo> repos) {
        ReposDiffCallback diffCallback = new ReposDiffCallback(this.repos, repos);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.repos = repos;
        diffResult.dispatchUpdatesTo(this);
    }

    public void swapRepos(int fromPosition, int toPosition) {
        Collections.swap(repos, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void removeRepo(int position) {
        repos.remove(position);
        notifyItemRemoved(position);
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        RepoItemBinding binding;

        RepoViewHolder(RepoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
