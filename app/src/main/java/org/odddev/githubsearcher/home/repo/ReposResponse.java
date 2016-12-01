package org.odddev.githubsearcher.home.repo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kenrube
 * @since 01.12.16
 */

public class ReposResponse {

    @SerializedName("total_count")
    @Expose
    private int totalCount;

    @SerializedName("incomplete_results")
    @Expose
    private boolean incompleteResults;

    @SerializedName("items")
    @Expose
    private List<Repo> repos = new ArrayList<>();

    public List<Repo> getRepos() {
        return repos;
    }
}
