package com.eventtus.simpletwitterclient.Models;

/**
 * Created by Bassam on 6/23/2016.
 */
public class UserTimeline {
    private String createdAt;
    private String text;
    private long id;
    private long retweet_count;
    private TwitterUser currentTwitter;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(long retweet_count) {
        this.retweet_count = retweet_count;
    }

    public TwitterUser getCurrentTwitterUser() {
        return currentTwitter;
    }

    public void setCurrentTwitterUser(TwitterUser currentTwitter) {
        this.currentTwitter = currentTwitter;
    }
}
