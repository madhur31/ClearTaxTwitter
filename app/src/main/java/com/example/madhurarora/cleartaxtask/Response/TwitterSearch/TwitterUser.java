package com.example.madhurarora.cleartaxtask.Response.TwitterSearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TwitterUser {

    @SerializedName("name")
    private String name;

    @SerializedName("profile_image_url")
    private String profile_image_url;

    public String getName() {
        return name;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }
}
