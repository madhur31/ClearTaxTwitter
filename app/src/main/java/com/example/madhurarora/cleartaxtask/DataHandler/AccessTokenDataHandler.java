package com.example.madhurarora.cleartaxtask.DataHandler;

import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.Request.AccessTokenRequest;
import com.example.madhurarora.cleartaxtask.Response.TwitterAccessToken.AccessToken;
import com.example.madhurarora.cleartaxtask.Utils.Constants;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
abstract public class AccessTokenDataHandler extends BaseDataHandler<AccessToken> {

    private String URL;
    private Map<String, String> headers;
    private String requestBody;
    private final String TAG = "TOKEN_REQUEST";

    public AccessTokenDataHandler(String URL, Map<String, String> headers, String requestBody) {
        this.URL = URL;
        this.headers = headers;
        this.requestBody = requestBody;
    }

    public void fetchData() {
        this.cType = new TypeToken<AccessToken>(){}.getType();
        AccessTokenRequest accessTokenRequest = new AccessTokenRequest(URL, headers, requestBody, listener, errorListener);
        this.request = accessTokenRequest;
        request.setTag(TAG);
        cancelAll(TAG);
        ApplicationClass.getInstance().addToRequestQueue(request);
    }

    @Override
    public void resultReceived(AccessToken response, boolean fromDB) {
        if(response != null) {
            resultReceivedAccessToken(Constants.RESULT_OK, "", response);
        }
        else {
            resultReceivedAccessToken(Constants.RESULT_ERROR, "ERROR", null);
        }
    }

    @Override
    public void errorRecieved(int responseCode, int errorCode, String errorMessage) {
        resultReceivedAccessToken(responseCode, errorMessage, null);
    }

    abstract public void resultReceivedAccessToken(int resultCode, String errorMessage, AccessToken response);
}
