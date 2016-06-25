package com.example.madhurarora.cleartaxtask.Volley;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.Utils.ResponseUtils;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class GsonRequest<T> extends Request<T> {

    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String Body_content_type =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private final Type classType;
    private final Response.Listener listener;
    private Gson gson;
    private String url = null;
    private int method;
    private String requestBody;
    private Map<String, String> headers;


    public GsonRequest(int method, String url, String requestBody, Type classType, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.method = method;
        this.classType = classType;
        this.listener = listener;
        this.url = url;
        this.requestBody = requestBody;
        this.headers = headers;
        gson = ApplicationClass.getGsonInstance();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        String data = new String(networkResponse.data);
        Log.d("Parse Network", data);
        try {
            if (networkResponse.statusCode != 200) {
                return Response.error(new VolleyError(networkResponse));
            }
            Reader jsonReader = ResponseUtils.getJsonReader(networkResponse);
            T response = gson.fromJson(jsonReader, classType);
            if (response == null)
                return Response.error(new VolleyError(networkResponse));
            return Response.success(response, null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.error(new VolleyError(networkResponse));
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        return headers;
    }

    @Override
    public String getBodyContentType() {
        return Body_content_type;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try{
            return  requestBody == null ? null :requestBody .getBytes(PROTOCOL_CHARSET);
        }catch (UnsupportedEncodingException ee) {
            Log.d("Unsupported Encoding", requestBody);
            return null;
        }
    }
}
