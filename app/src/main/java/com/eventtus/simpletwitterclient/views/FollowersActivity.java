package com.eventtus.simpletwitterclient.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eventtus.simpletwitterclient.Models.FollowersResult;
import com.eventtus.simpletwitterclient.Models.TwitterUser;
import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.controls.EndlessRecyclerViewScrollListener;
import com.eventtus.simpletwitterclient.controls.RecyclerItemClickListener;
import com.eventtus.simpletwitterclient.controls.adapters.TwitterFollowersUsersAdapter;
import com.eventtus.simpletwitterclient.controls.twitter.TwitterFollowersApiClient;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;
import com.eventtus.simpletwitterclient.helpers.InternalStorage;
import com.eventtus.simpletwitterclient.helpers.SharedPreferencesHelper;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.fabric.sdk.android.Fabric;
import retrofit.client.Response;

/**
 * Created by Bassam on 6/21/2016.
 */
public class FollowersActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    private TwitterUser currentTwitterUser;
    private RecyclerView recFollowersList;
    private SwipeRefreshLayout swipeRefreshLayoutFollowersList;
    private long cursor;
    private long nextCursor;
    private TwitterFollowersUsersAdapter twitterFollowersUsersAdapter;
    private View progressIndecator;
    private View circularProgressLoadingFirstTime;

    public void setProfileImage() {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.e("HIYA", "onBitmapLoaded");
                Bitmap b = Bitmap.createScaledBitmap(bitmap, 120, 120, false);
                BitmapDrawable icon = new BitmapDrawable(toolbar.getResources(), b);
                toolbar.setLogo(icon);
                toolbar.invalidate();
                FollowersActivity.this.setSupportActionBar(toolbar);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e("HIYA", "onBitmapFailed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e("HIYA", "onPrepareLoad");
            }
        };
        Picasso.with(toolbar.getContext())
                .load(currentTwitterUser.getProfileImageUrl())
                .into(target);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        currentTwitterUser = SharedPreferencesHelper.getSavedTwitterUserFrom(FollowersActivity.this);
        initToolBar(currentTwitterUser.getName());
        initFollowersRecycleList();
        initHorizontalLoadMoreProgressIndicator();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));
        Fabric.with(this, new Twitter(authConfig));
        cursor = -1;
        fillFollowersListFromCashing();
        showFollowers(currentTwitterUser.getId(), currentTwitterUser.getName(), -1, SharedPreferencesHelper.getAuthenticationToken(FollowersActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowersActivity.this));

    }

    private void fillFollowersListFromCashing() {
        try {
            FollowersResult cachedNews = (FollowersResult) InternalStorage.readObject(FollowersActivity.this, currentTwitterUser.getScreenName());
            twitterFollowersUsersAdapter = new TwitterFollowersUsersAdapter(FollowersActivity.this, cachedNews.getUsers());
            recFollowersList.setAdapter(twitterFollowersUsersAdapter);
            progressIndecator.setVisibility(View.GONE);
            circularProgressLoadingFirstTime.setVisibility(View.GONE);
        } catch (Exception ex) {
        }
    }

    private void initHorizontalLoadMoreProgressIndicator() {
        progressIndecator = findViewById(R.id.progress_followers_load_more);
        circularProgressLoadingFirstTime = findViewById(R.id.progress_first_time_loading);
        circularProgressLoadingFirstTime.setVisibility(View.VISIBLE);

    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar_followers_list);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        setProfileImage();
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );
    }

    private void initFollowersRecycleList() {
        swipeRefreshLayoutFollowersList = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_followers_list);
        swipeRefreshLayoutFollowersList.setOnRefreshListener(FollowersActivity.this);
        swipeRefreshLayoutFollowersList.setColorSchemeResources(R.color.colorAccent);
        recFollowersList = (RecyclerView) findViewById(R.id.lst_followers);
        recFollowersList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recFollowersList.setLayoutManager(llm);
        recFollowersList.addOnItemTouchListener(
                new RecyclerItemClickListener(FollowersActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        GeneralMethods.printLog("=====", "item selected" + position);
                        TwitterUser selectedTwitterUser = twitterFollowersUsersAdapter.getTwittersList(position);
                        Intent intentSelectedUserProfile = new Intent(FollowersActivity.this, FollowerUserProfileActivity.class);
                        intentSelectedUserProfile.putExtra(FollowerUserProfileActivity.SELECTED_USER_FLAG, selectedTwitterUser);
                        FollowersActivity.this.startActivity(intentSelectedUserProfile);
                    }
                })
        );
        recFollowersList.addOnScrollListener(new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                progressIndecator.setVisibility(View.VISIBLE);
                showFollowers(currentTwitterUser.getId(), currentTwitterUser.getName(), nextCursor, SharedPreferencesHelper.getAuthenticationToken(FollowersActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowersActivity.this));
            }
        });
    }

    private void showFollowers(long userID, String username, final long cursor, String authToken, String authSecret) {
//         GeneralMethods.printLog("ddd",result.data.getUserName()+"-"+ result.data.getUserId());
        swipeRefreshLayoutFollowersList.setRefreshing(true);
        TwitterAuthToken twitterAuthToken = new TwitterAuthToken(authToken, authSecret);
        TwitterSession session = new TwitterSession(twitterAuthToken, userID, username);

        TwitterFollowersApiClient twitterFollowersApiClient = new TwitterFollowersApiClient(session);
        twitterFollowersApiClient.getCustomService().list(userID, cursor, new Callback<Response>() {

            @Override
            public void failure(TwitterException arg0) {
                // TODO Auto-generated method stub
                GeneralMethods.printLog("====failure", arg0.getMessage());
                Toast.makeText(FollowersActivity.this, arg0.getMessage(), Toast.LENGTH_LONG).show();
                swipeRefreshLayoutFollowersList.setRefreshing(false);
                progressIndecator.setVisibility(View.GONE);
                circularProgressLoadingFirstTime.setVisibility(View.GONE);
            }

            @Override
            public void success(Result<Response> arg0) {
                // TODO Auto-generated method stub
                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {
                    reader = new BufferedReader(new InputStreamReader(arg0.response.getBody().in()));
                    String line;
                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String result = sb.toString();
                applyFollowersParsersAndFillList(result, cursor);
/**
 * If we want to parse json response manually.
 */
//                try {
//                    JSONObject obj = new JSONObject(result);
//                    JSONArray ids = obj.getJSONArray("users");
//                    //This is where we get ids of followers
//                    for (int i = 0; i < ids.length(); i++) {
//                        GeneralMethods.printLog("ddd", "Id of user " + (i + 1) + " is " + ids.getJSONObject(i).getString("name"));
//                    }
//                    String nextCursor = obj.getString("next_cursor_str");
//                    GeneralMethods.printLog("====next_cursor_str", nextCursor);
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }

        });
    }

    private void applyFollowersParsersAndFillList(String result, long cursor) {
        GeneralMethods.printLog("Response is>>>>>>>>>", result);
        Gson gson = new Gson();
        FollowersResult response = gson.fromJson(result, FollowersResult.class);

        nextCursor = response.getNextCursor();
        if (cursor == -1) {
            addFollowingResponseToCash(response);
            twitterFollowersUsersAdapter = new TwitterFollowersUsersAdapter(FollowersActivity.this, response.getUsers());
            recFollowersList.setAdapter(twitterFollowersUsersAdapter);
        } else {
            twitterFollowersUsersAdapter.addFollowers(response.getUsers());
        }
        swipeRefreshLayoutFollowersList.setRefreshing(false);
        progressIndecator.setVisibility(View.GONE);
        circularProgressLoadingFirstTime.setVisibility(View.GONE);
    }

    private void addFollowingResponseToCash(FollowersResult response) {
        try {
            InternalStorage.addTwitterFollowesWriteObject(FollowersActivity.this, currentTwitterUser.getScreenName(), response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        cursor = -1;
        showFollowers(currentTwitterUser.getId(), currentTwitterUser.getName(), cursor, SharedPreferencesHelper.getAuthenticationToken(FollowersActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowersActivity.this));
    }
}
