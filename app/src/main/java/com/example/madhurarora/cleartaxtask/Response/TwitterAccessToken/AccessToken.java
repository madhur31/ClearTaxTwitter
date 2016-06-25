package com.example.madhurarora.cleartaxtask.Response.TwitterAccessToken;

import com.google.gson.annotations.SerializedName;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class AccessToken {

    @SerializedName("access_token")
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }
}
