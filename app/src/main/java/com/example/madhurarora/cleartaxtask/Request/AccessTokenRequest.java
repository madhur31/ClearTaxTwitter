package com.example.madhurarora.cleartaxtask.Request;

import com.android.volley.Response;
import com.example.madhurarora.cleartaxtask.Response.TwitterAccessToken.AccessToken;
import com.example.madhurarora.cleartaxtask.Volley.GsonRequest;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class AccessTokenRequest extends GsonRequest<AccessToken> {

    public AccessTokenRequest(String URL, Map<String, String> headers, String requestBody, Response.Listener<AccessToken> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, requestBody, new TypeToken<AccessToken>(){}.getType(), headers, listener, errorListener);
    }
}
