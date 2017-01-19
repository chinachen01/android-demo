package com.example.chenyong.android_demo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by focus on 17/1/18.
 */

public class GitUser {

    /**
     * login : xiaoxiyang09
     * id : 13019664
     * avatar_url : https://avatars.githubusercontent.com/u/13019664?v=3
     * gravatar_id :
     * url : https://api.github.com/users/xiaoxiyang09
     * html_url : https://github.com/xiaoxiyang09
     * followers_url : https://api.github.com/users/xiaoxiyang09/followers
     * following_url : https://api.github.com/users/xiaoxiyang09/following{/other_user}
     * gists_url : https://api.github.com/users/xiaoxiyang09/gists{/gist_id}
     * starred_url : https://api.github.com/users/xiaoxiyang09/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/xiaoxiyang09/subscriptions
     * organizations_url : https://api.github.com/users/xiaoxiyang09/orgs
     * repos_url : https://api.github.com/users/xiaoxiyang09/repos
     * events_url : https://api.github.com/users/xiaoxiyang09/events{/privacy}
     * received_events_url : https://api.github.com/users/xiaoxiyang09/received_events
     * type : User
     * site_admin : false
     * name : null
     * company : null
     * blog : null
     * location : null
     * email : xiaoxiyang09@163.com
     * hireable : null
     * bio : null
     * public_repos : 25
     * public_gists : 0
     * followers : 1
     * following : 2
     * created_at : 2015-06-23T15:26:03Z
     * updated_at : 2016-12-28T09:18:51Z
     */

    @SerializedName("login")
    public String login;
    @SerializedName("id")
    public int id;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("gravatar_id")
    public String gravatarId;
    @SerializedName("url")
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    @SerializedName("followers_url")
    public String followersUrl;
    @SerializedName("following_url")
    public String followingUrl;
    @SerializedName("gists_url")
    public String gistsUrl;
    @SerializedName("starred_url")
    public String starredUrl;
    @SerializedName("subscriptions_url")
    public String subscriptionsUrl;
    @SerializedName("organizations_url")
    public String organizationsUrl;
    @SerializedName("repos_url")
    public String reposUrl;
    @SerializedName("events_url")
    public String eventsUrl;
    @SerializedName("received_events_url")
    public String receivedEventsUrl;
    @SerializedName("type")
    public String type;
    @SerializedName("site_admin")
    public boolean siteAdmin;
    @SerializedName("name")
    public Object name;
    @SerializedName("company")
    public Object company;
    @SerializedName("blog")
    public Object blog;
    @SerializedName("location")
    public Object location;
    @SerializedName("email")
    public String email;
    @SerializedName("hireable")
    public Object hireable;
    @SerializedName("bio")
    public Object bio;
    @SerializedName("public_repos")
    public int publicRepos;
    @SerializedName("public_gists")
    public int publicGists;
    @SerializedName("followers")
    public int followers;
    @SerializedName("following")
    public int following;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;

    @Override
    public String toString() {
        return "GitUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gravatarId='" + gravatarId + '\'' +
                ", url='" + url + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                ", followersUrl='" + followersUrl + '\'' +
                ", followingUrl='" + followingUrl + '\'' +
                ", gistsUrl='" + gistsUrl + '\'' +
                ", starredUrl='" + starredUrl + '\'' +
                ", subscriptionsUrl='" + subscriptionsUrl + '\'' +
                ", organizationsUrl='" + organizationsUrl + '\'' +
                ", reposUrl='" + reposUrl + '\'' +
                ", eventsUrl='" + eventsUrl + '\'' +
                ", receivedEventsUrl='" + receivedEventsUrl + '\'' +
                ", type='" + type + '\'' +
                ", siteAdmin=" + siteAdmin +
                ", name=" + name +
                ", company=" + company +
                ", blog=" + blog +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", hireable=" + hireable +
                ", bio=" + bio +
                ", publicRepos=" + publicRepos +
                ", publicGists=" + publicGists +
                ", followers=" + followers +
                ", following=" + following +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
