package com.eventtus.simpletwitterclient.controls.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventtus.simpletwitterclient.Models.UserTimeline;
import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.helpers.RoundedCornersTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bassam on 6/22/2016.
 */
public class UsersTweetsAdapter extends RecyclerView.Adapter<UsersTweetsAdapter.TweetsHolders> {

    private final Context context;
    private final RoundedCornersTransformation roundedCornersTransformation;
    private List<UserTimeline> tweetsList;

    public UsersTweetsAdapter(Context currentContext, List<UserTimeline> contactList) {
        this.tweetsList = contactList;
        this.context = currentContext;
        final int radius = 15;
        final int margin = 10;
        roundedCornersTransformation = new RoundedCornersTransformation(radius, margin);
    }

    @Override
    public int getItemCount() {
        return tweetsList.size();
    }

    @Override
    public void onBindViewHolder(TweetsHolders currentTweetsHolders, int i) {
        UserTimeline currentTweet = tweetsList.get(i);
        currentTweetsHolders.tvUserFullName.setText(currentTweet.getCurrentTwitterUser().getName());
        currentTweetsHolders.tvUserHandle.setText("@" + currentTweet.getCurrentTwitterUser().getScreenName());

        currentTweetsHolders.tvTweets.setText(currentTweet.getText());
        currentTweetsHolders.tvTweets.setVisibility(View.VISIBLE);
        Picasso.with(context).load(currentTweet.getCurrentTwitterUser().getProfileImageUrl()).transform(roundedCornersTransformation).placeholder(R.drawable.ic_profile_dummy).into(currentTweetsHolders.imgUserProfile);

    }

    @Override
    public TweetsHolders onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.tweets_item_cardview, viewGroup, false);

        return new TweetsHolders(itemView);
    }

    public void addTweets(List<UserTimeline> userTimelineList) {
        tweetsList.addAll(userTimelineList);
        notifyDataSetChanged();
    }

    public UserTimeline getTweetsList(int position) {
        return tweetsList.get(position);
    }

    public static class TweetsHolders extends RecyclerView.ViewHolder {
        protected ImageView imgUserProfile;
        protected TextView tvUserFullName;
        protected TextView tvUserHandle;
        protected TextView tvTweets;

        public TweetsHolders(View v) {
            super(v);
            imgUserProfile = (ImageView) v.findViewById(R.id.img_user_profile);
            tvUserFullName = (TextView) v.findViewById(R.id.txt_user_full_name);
            tvUserHandle = (TextView) v.findViewById(R.id.txt_user_handel);
            tvTweets = (TextView) v.findViewById(R.id.txt_tweet);
        }
    }
}

