package org.odddev.githubsearcher.home.repo;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.odddev.githubsearcher.R;
import org.odddev.githubsearcher.databinding.RepoItemBinding;

import java.util.ArrayList;
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
        this.repos = repos;
        notifyDataSetChanged();
    }

    public void addRepos(List<Repo> repos) {
        this.repos.addAll(repos);
        notifyItemRangeInserted(this.repos.size(), repos.size());
    }

    class RepoViewHolder extends RecyclerView.ViewHolder {

        RepoItemBinding binding;

        RepoViewHolder(RepoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
