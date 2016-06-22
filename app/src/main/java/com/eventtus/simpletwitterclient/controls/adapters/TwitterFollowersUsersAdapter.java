package com.eventtus.simpletwitterclient.controls.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eventtus.simpletwitterclient.Models.TwitterUser;
import com.eventtus.simpletwitterclient.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bassam on 6/22/2016.
 */
public class TwitterFollowersUsersAdapter extends RecyclerView.Adapter<TwitterFollowersUsersAdapter.TwitterFollowersUsersHolders> {

    private final Context context;
    private List<TwitterUser> twitterFollowers;

    public TwitterFollowersUsersAdapter(Context currentContext, List<TwitterUser> contactList) {
        this.twitterFollowers = contactList;
        this.context=currentContext;
    }

    @Override
    public int getItemCount() {
        return twitterFollowers.size();
    }

    @Override
    public void onBindViewHolder(TwitterFollowersUsersAdapter.TwitterFollowersUsersHolders currentTwitterFollowersUsersHolders, int i) {
        TwitterUser currentTwitterUser = twitterFollowers.get(i);
        currentTwitterFollowersUsersHolders.tvFollowersFullName.setText(currentTwitterUser.getName());
        currentTwitterFollowersUsersHolders.tvHandle.setText("@"+currentTwitterUser.getScreenName());
        if (currentTwitterUser.getDescription() == null || currentTwitterUser.getDescription().isEmpty()) {
        } else {
            currentTwitterFollowersUsersHolders.tvBio.setText(currentTwitterUser.getDescription());
            currentTwitterFollowersUsersHolders.tvBio.setVisibility(View.VISIBLE);
        }
        Picasso.with(context).load(currentTwitterUser.getProfileImageUrl()).placeholder(R.drawable.ic_profile_dummy).into(currentTwitterFollowersUsersHolders.imgFollowerProfile);

    }

    @Override
    public TwitterFollowersUsersHolders onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.follower_item_cardview, viewGroup, false);

        return new TwitterFollowersUsersHolders(itemView);
    }

    public void addFollowers(List<TwitterUser> users) {
        twitterFollowers.addAll(users);
        notifyDataSetChanged();
    }

    public static class TwitterFollowersUsersHolders extends RecyclerView.ViewHolder {
        protected ImageView imgFollowerProfile;
        protected TextView tvFollowersFullName;
        protected TextView tvHandle;
        protected TextView tvBio;

        public TwitterFollowersUsersHolders(View v) {
            super(v);
            imgFollowerProfile = (ImageView) v.findViewById(R.id.img_follower_profile);
            tvFollowersFullName = (TextView) v.findViewById(R.id.txtFollowerFullName);
            tvHandle = (TextView) v.findViewById(R.id.txtFollowerHandel);
            tvBio = (TextView) v.findViewById(R.id.txtBio);
        }
    }
}

