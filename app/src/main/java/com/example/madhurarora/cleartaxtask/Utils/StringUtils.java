package com.example.madhurarora.cleartaxtask.Utils;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class StringUtils {

    public static boolean isNullorEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
    }
}
