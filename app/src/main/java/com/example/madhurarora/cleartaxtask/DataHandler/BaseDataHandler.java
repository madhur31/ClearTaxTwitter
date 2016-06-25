package com.example.madhurarora.cleartaxtask.DataHandler;

import android.util.Log;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.Utils.ResponseUtils;
import com.google.gson.Gson;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Created by madhur.arora on 25/06/16.
 */
abstract public class BaseDataHandler<T> {

    protected Response.Listener<T> listener;
    protected Response.ErrorListener errorListener;

    protected Request request;

    Type cType;
    Gson gson;

    public BaseDataHandler() {

        gson = ApplicationClass.getGsonInstance();

        try {
            errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    if (error.networkResponse != null)
                        Log.d("BaseDataHandler", error.toString() + error.networkResponse.statusCode);
                    if (error instanceof TimeoutError) {
                        errorRecieved(504, -1, "Request Timeout!");
                    } else if (error instanceof NoConnectionError) {
                        errorRecieved(-1, -1, "Internet Connection not available");
                    } else if (error.networkResponse != null) {
                        Reader jsonReader = ResponseUtils.getJsonReader(error.networkResponse);
                        if (error.networkResponse.statusCode == 304) {
                            T response = gson.fromJson(jsonReader, cType);
                            if (response != null) {
                                resultReceived(response, false);
                                //break;
                            }
                        } else if (error.networkResponse.statusCode == 500) {
                            errorRecieved(500, -1, "Oops ! Something wrong happened.");
                        } else if (error.networkResponse.statusCode == 400) {
                            errorRecieved(500, -1, "Oops ! Something wrong happened.");
                        } else if (error.networkResponse.statusCode == 415) {
                            errorRecieved(415, -1, "Oops ! Something wrong happened.");
                        } else if (error.networkResponse.statusCode == 405) {
                            errorRecieved(405, -1, "Oops ! Something wrong happened.");
                        } else {
                            errorRecieved(999, -1, "Oops ! Something wrong happened.");
                        }
                    } else {
                        errorRecieved(999, -1, "Oops ! Something wrong happened.");
                    }
                }
            };

            listener = new Response.Listener<T>() {
                @Override
                public void onResponse(T response) {
                    Log.d("RESPONSE", "In on Response");
                    resultReceived(response, false);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelAll(String TAG) {
        ApplicationClass.getInstance().getRequestQueue().cancelAll(TAG);
    }

    abstract public void resultReceived(T response, boolean fromDB);

    abstract public void errorRecieved(int responseCode, int errorCode, String errorMessage);
}
