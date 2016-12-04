package org.odddev.githubsearcher.home.repo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author kenrube
 * @since 01.12.16
 */

public class Repo {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("full_name")
    @Expose
    private String fullName;

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("private")
    @Expose
    private boolean _private;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("fork")
    @Expose
    private boolean fork;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("pushed_at")
    @Expose
    private String pushedAt;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("size")
    @Expose
    private int size;

    @SerializedName("stargazers_count")
    @Expose
    private int stargazersCount;

    @SerializedName("watchers_count")
    @Expose
    private int watchersCount;

    @SerializedName("language")
    @Expose
    private String language;

    @SerializedName("has_issues")
    @Expose
    private boolean hasIssues;

    @SerializedName("has_downloads")
    @Expose
    private boolean hasDownloads;

    @SerializedName("has_wiki")
    @Expose
    private boolean hasWiki;

    @SerializedName("has_pages")
    @Expose
    private boolean hasPages;

    @SerializedName("forks_count")
    @Expose
    private int forksCount;

    @SerializedName("open_issues_count")
    @Expose
    private int openIssuesCount;

    @SerializedName("forks")
    @Expose
    private int forks;

    @SerializedName("open_issues")
    @Expose
    private int openIssues;

    @SerializedName("watchers")
    @Expose
    private int watchers;

    @SerializedName("default_branch")
    @Expose
    private String defaultBranch;

    @SerializedName("score")
    @Expose
    private float score;

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getStargazersCount() {
        return stargazersCount;
    }

    public Integer getForksCount() {
        return forksCount;
    }
}
