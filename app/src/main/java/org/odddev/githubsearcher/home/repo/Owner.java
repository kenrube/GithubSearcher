package org.odddev.githubsearcher.home.repo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author kenrube
 * @since 01.12.16
 */

public class Owner {

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    @SerializedName("gravatar_id")
    @Expose
    private String gravatarId;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @SerializedName("followers_url")
    @Expose
    private String followersUrl;

    @SerializedName("following_url")
    @Expose
    private String followingUrl;

    @SerializedName("gists_url")
    @Expose
    private String gistsUrl;

    @SerializedName("starred_url")
    @Expose
    private String starredUrl;

    @SerializedName("subscriptions_url")
    @Expose
    private String subscriptionsUrl;

    @SerializedName("organizations_url")
    @Expose
    private String organizationsUrl;

    @SerializedName("repos_url")
    @Expose
    private String reposUrl;

    @SerializedName("events_url")
    @Expose
    private String eventsUrl;

    @SerializedName("received_events_url")
    @Expose
    private String receivedEventsUrl;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("site_admin")
    @Expose
    private boolean siteAdmin;

    public String getAvatarUrl() {
        return avatarUrl;
    }
}