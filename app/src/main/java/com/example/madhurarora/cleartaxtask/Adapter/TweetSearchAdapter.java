package com.example.madhurarora.cleartaxtask.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.example.madhurarora.cleartaxtask.Application.ApplicationClass;
import com.example.madhurarora.cleartaxtask.R;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetStatus;
import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TwitterUser;
import com.example.madhurarora.cleartaxtask.Utils.TopThreeWords;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TweetSearchAdapter extends RecyclerView.Adapter<TweetSearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TweetStatus> status;

    public TweetSearchAdapter(ArrayList<TweetStatus> status) {
        if (status != null) {
            this.status = filterList(status);
        } else {
            this.status = new ArrayList<>();
        }
    }

    public void setStatus(ArrayList<TweetStatus> status) {
        this.status = filterList(status);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TweetStatus tweetStatus = status.get(position);

        try {
            String dateString = tweetStatus.getCreated_at();
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date = (Date) formatter.parse(dateString);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM");
            dateString = simpleDateFormat.format(date);
            holder.date.setText(dateString);
        }catch (Exception e) {
            e.printStackTrace();
        }

        holder.title.setText(tweetStatus.getText());

        TwitterUser user = tweetStatus.getUser();

        holder.userName.setText(user.getName());
        holder.profileImage.setImageUrl(user.getProfile_image_url(), ApplicationClass.getInstance().getImageLoader());
        holder.profileImage.setDefaultImageResId(R.drawable.defaultprofilepic);
    }

    @Override
    public int getItemCount() {
        return status.size();
    }

    public ArrayList<TweetStatus> filterList(ArrayList<TweetStatus> status) {
        ArrayList<TweetStatus> tempList = new ArrayList<>();
        for (TweetStatus tweetStatus : status) {
            String text = tweetStatus.getText();
            if (TopThreeWords.checktags(text)) {
                tempList.add(tweetStatus);
            }
        }
        return tempList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView userName;
        TextView date;
        NetworkImageView profileImage;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.list_title);
            userName = (TextView) view.findViewById(R.id.userName);
            date = (TextView) view.findViewById(R.id.tweetDate);
            profileImage = (NetworkImageView) view.findViewById(R.id.profileImage);
        }
    }
}
