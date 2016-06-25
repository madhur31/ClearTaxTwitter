package com.example.madhurarora.cleartaxtask.Response.TwitterSearch;

import com.google.gson.annotations.SerializedName;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class HashTags {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }
}
