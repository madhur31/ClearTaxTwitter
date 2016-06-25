package com.example.madhurarora.cleartaxtask.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.madhurarora.cleartaxtask.Adapter.TweetSearchAdapter;
import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.DataHandler.TweetSearchDataHandler;
import com.example.madhurarora.cleartaxtask.R;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetSearch;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetStatus;
import com.example.madhurarora.cleartaxtask.Utils.Constants;
import com.example.madhurarora.cleartaxtask.Utils.PrefrenceData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TweetsFragment extends Fragment {

    TweetSearchDataHandler tweetSearchDataHandler;
    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TweetSearchAdapter adapter;
    private ProgressBar progressBar;
    private ArrayList<TweetStatus> status;
    private Map<String, Integer> countMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_tweets, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.tweets_list);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TweetSearchAdapter(status);

        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void UpdateUI(ArrayList<TweetStatus> status) {
        if (adapter == null) {
            adapter = new TweetSearchAdapter(status);
        }
        adapter.setStatus(status);
        adapter.notifyDataSetChanged();
    }
}
