package com.example.madhurarora.cleartaxtask.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class PrefrenceData {

    public static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static String getAuthToken(Context ctx) {
        return getSharedPreferences(ctx).getString("AuthToken", "");
    }

    public static void setAuthToken(Context context, String authToken) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString("AuthToken", authToken);
        editor.apply();
    }

    public static void clearAll(Context context) {
        setAuthToken(context, "");
    }
}
