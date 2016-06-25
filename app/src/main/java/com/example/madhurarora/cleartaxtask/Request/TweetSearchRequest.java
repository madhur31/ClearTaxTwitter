package com.example.madhurarora.cleartaxtask.Request;

import com.android.volley.Response;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetSearch;
import com.example.madhurarora.cleartaxtask.Volley.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TweetSearchRequest extends GsonRequest<TweetSearch> {

    public TweetSearchRequest(String URL, Map<String, String> headers, Response.Listener<TweetSearch> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, "", new TypeToken<TweetSearch>(){}.getType(), headers, listener, errorListener);
    }
}
