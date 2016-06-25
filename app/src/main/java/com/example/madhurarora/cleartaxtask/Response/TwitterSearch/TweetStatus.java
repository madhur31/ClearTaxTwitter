package com.example.madhurarora.cleartaxtask.Response.TwitterSearch;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TweetStatus {

    @SerializedName("text")
    private String text;

    @SerializedName("user")
    private TwitterUser user;

    @SerializedName("created_at")
    private String created_at;

    public TwitterUser getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getCreated_at() {
        return created_at;
    }
}
