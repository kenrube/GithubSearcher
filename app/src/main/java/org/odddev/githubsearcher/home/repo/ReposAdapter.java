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

    public ReposAdapter() {
        repos = new ArrayList<>();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RepoViewHolder(DataBindingUtil.inflate(inflater, R.layout.repo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.binding.setRepo(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public void setRepos(List<Repo> repos) {
        ReposDiffCallback diffCallback = new ReposDiffCallback(this.repos, repos);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.repos = repos;
        diffResult.dispatchUpdatesTo(this);
    }

    public void addRepos(List<Repo> repos) {
        List<Repo> temp = new ArrayList<>();
        temp.addAll(this.repos);
        temp.addAll(repos);

        ReposDiffCallback diffCallback = new ReposDiffCallback(this.repos, temp);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.repos = temp;
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
