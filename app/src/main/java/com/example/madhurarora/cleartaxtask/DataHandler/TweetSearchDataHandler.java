package com.example.madhurarora.cleartaxtask.DataHandler;

import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.Request.TweetSearchRequest;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetSearch;
import com.example.madhurarora.cleartaxtask.Utils.Constants;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
abstract public class TweetSearchDataHandler extends BaseDataHandler<TweetSearch> {

    private final String TAG = "SEARCH";
    private String URL;
    private Map<String, String> headers;

    public TweetSearchDataHandler(String URL, Map<String, String> headers) {
        this.URL = URL;
        this.headers = headers;
    }

    public void fetchData() {
        this.cType = new TypeToken<TweetSearch>(){}.getType();
        TweetSearchRequest tweetSearchRequest = new TweetSearchRequest(URL, headers, listener, errorListener);
        this.request = tweetSearchRequest;
        this.request.setTag(TAG);
        cancelAll(TAG);
        ApplicationClass.getInstance().addToRequestQueue(request);
    }

    @Override
    public void resultReceived(TweetSearch response, boolean fromDB) {
        if(response != null) {
            resultRecievedTweetSearch(Constants.RESULT_OK, "", response);
        } else {
            resultRecievedTweetSearch(Constants.RESULT_ERROR, "ERROR", null);
        }
    }

    @Override
    public void errorRecieved(int responseCode, int errorCode, String errorMessage) {
        resultRecievedTweetSearch(responseCode, errorMessage, null);
    }

    abstract public void resultRecievedTweetSearch(int resultCode, String errorMessage, TweetSearch response);
}
