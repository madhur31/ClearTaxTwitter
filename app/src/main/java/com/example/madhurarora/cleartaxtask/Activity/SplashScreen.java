package com.example.madhurarora.cleartaxtask.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.DataHandler.AccessTokenDataHandler;
import com.example.madhurarora.cleartaxtask.R;
import com.example.madhurarora.cleartaxtask.Response.TwitterAccessToken.AccessToken;
import com.example.madhurarora.cleartaxtask.Utils.Constants;
import com.example.madhurarora.cleartaxtask.Utils.PrefrenceData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class SplashScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_splash_screen);

        TextView txt = (TextView) findViewById(R.id.title_text);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Mika Melvas - RionaSans-RegularItalic.ttf");
        txt.setTypeface(font);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAuthToken();
    }


    private void getAuthToken() {
        String CombinedKey = Constants.API_KEY + ":" + Constants.API_SECRET;
        String base64Encoded = Base64.encodeToString(CombinedKey.getBytes(), Base64.NO_WRAP);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + base64Encoded);
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        String requestBody = "grant_type=client_credentials";

        AccessTokenDataHandler accessTokenDataHandler = new AccessTokenDataHandler(Constants.OAUTH_URL, headers, requestBody) {
            @Override
            public void resultReceivedAccessToken(int resultCode, String errorMessage, AccessToken response) {
                if(resultCode == Constants.RESULT_OK && response != null) {
                    PrefrenceData.setAuthToken(ApplicationClass.getAppContext(), response.getAccess_token());

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(ApplicationClass.getAppContext(), errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        };
        accessTokenDataHandler.fetchData();
    }
}
