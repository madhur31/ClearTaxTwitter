package com.example.madhurarora.cleartaxtask.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madhurarora.cleartaxtask.R;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TopThreeFragment extends Fragment {

    View view;
    TextView value;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_top_three, container, false);

        value = (TextView) view.findViewById(R.id.top_three);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setTextView(String topThree) {
        if (value != null) {
            value.setText(topThree);
        }
    }
}
