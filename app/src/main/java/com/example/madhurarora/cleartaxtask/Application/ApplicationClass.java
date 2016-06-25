package com.example.madhurarora.cleartaxtask.Application;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.madhurarora.cleartaxtask.Utils.LruBitmapCache;
import com.example.madhurarora.cleartaxtask.Utils.NukeSSLCerts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class ApplicationClass extends Application {

    public static final String TAG = ApplicationClass.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context context;
    private static ApplicationClass mInstance;
    private static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationClass.context = getApplicationContext();
        initGson();
        mInstance = this;
        NukeSSLCerts.nuke();
    }

    public static Context getAppContext() {
        return ApplicationClass.context;
    }

    public static Gson getGsonInstance() {
        return ApplicationClass.gson;
    }

    private void initGson() {
        gson = new Gson();
    }

    public static synchronized ApplicationClass getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null)
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
