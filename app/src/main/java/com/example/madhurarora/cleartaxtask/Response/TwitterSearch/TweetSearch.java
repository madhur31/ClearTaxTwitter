package com.example.madhurarora.cleartaxtask.Response.TwitterSearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TweetSearch {

    @SerializedName("statuses")
    private ArrayList<TweetStatus> statuses;

    public ArrayList<TweetStatus> getStatuses() {
        return statuses;
    }
}
