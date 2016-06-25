package com.example.madhurarora.cleartaxtask.Activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.DataHandler.TweetSearchDataHandler;
import com.example.madhurarora.cleartaxtask.Fragments.TopThreeFragment;
import com.example.madhurarora.cleartaxtask.Fragments.TweetsFragment;
import com.example.madhurarora.cleartaxtask.R;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetSearch;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetStatus;
import com.example.madhurarora.cleartaxtask.Utils.Constants;
import com.example.madhurarora.cleartaxtask.Utils.PrefrenceData;
import com.example.madhurarora.cleartaxtask.Utils.TopThreeWords;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class MainActivity extends AppCompatActivity {

    private final int NUM_TABS = 2;
    TweetSearchDataHandler tweetSearchDataHandler;
    private ArrayList<TweetStatus> status;

    private TweetsFragment tweetsFragment;
    private TopThreeFragment topThreeFragment;
    private ProgressBar progressBar;
    private TextView title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.actionProgressBar);
        title = (TextView) findViewById(R.id.titleBar);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Mika Melvas - RionaSans-RegularItalic.ttf");
        title.setText(R.string.activity_main);
        title.setTypeface(font);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        //supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void getData() {
        progressBar.setVisibility(View.VISIBLE);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefrenceData.getAuthToken(ApplicationClass.getAppContext()));

        tweetSearchDataHandler = new TweetSearchDataHandler(Constants.SEARCH_URL, headers) {
            @Override
            public void resultRecievedTweetSearch(int resultCode, String errorMessage, TweetSearch response) {
                if (resultCode == Constants.RESULT_OK && response != null) {
                    status = response.getStatuses();
                    UpdateUI(status);
                    progressBar.setVisibility(View.GONE);
                }
            }
        };
        tweetSearchDataHandler.fetchData();
    }

    private void UpdateUI(ArrayList<TweetStatus> status) {
        if (tweetsFragment == null) {
            tweetsFragment = new TweetsFragment();
        }
        tweetsFragment.UpdateUI(status);
        TopThreeWords words = new TopThreeWords();
        String test = words.topThreeWords(status);
        //Toast.makeText(ApplicationClass.getAppContext(), test, Toast.LENGTH_SHORT).show();
        if(topThreeFragment == null) {
            topThreeFragment = new TopThreeFragment();
        }
        topThreeFragment.setTextView(test);
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (tweetsFragment == null) {
                        tweetsFragment = new TweetsFragment();
                    }
                    return tweetsFragment;
                case 1:
                    if (topThreeFragment == null) {
                        topThreeFragment = new TopThreeFragment();
                    }
                    return topThreeFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_TABS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Tweets";

                case 1:
                    return "Top Words";

                default:
                    return null;
            }
        }
    }
}
