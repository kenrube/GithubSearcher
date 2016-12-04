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
