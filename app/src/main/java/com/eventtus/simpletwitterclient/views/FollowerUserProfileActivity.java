package com.eventtus.simpletwitterclient.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.eventtus.simpletwitterclient.Models.TwitterUser;
import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.controls.twitter.UserTweetsApiClient;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;
import com.eventtus.simpletwitterclient.helpers.SharedPreferencesHelper;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.client.Response;

/**
 * Created by Bassam on 6/22/2016.
 */
public class FollowerUserProfileActivity extends AppCompatActivity {
    public static final String SELECTED_USER_FLAG = "SELECTED_USER_FLAG";
    private TwitterUser currentFollowedUser;
    private TwitterUser currentTwitterUser;
    private Toolbar toolbar;
    private ImageView imgVUserCover;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        imgVUserCover = (ImageView) findViewById(R.id.imgv_user_cover);
        currentFollowedUser = (TwitterUser) getIntent().getSerializableExtra(SELECTED_USER_FLAG);
        Picasso.with(FollowerUserProfileActivity.this).load(currentFollowedUser.getProfileBannerUrl()).into(imgVUserCover);
        GeneralMethods.printLog("====Current user", currentFollowedUser.getName());
        currentTwitterUser = SharedPreferencesHelper.getSavedTwitterUserFrom(FollowerUserProfileActivity.this);
        initToolBar(currentFollowedUser.getName());
        showSelectedUserTweets(currentFollowedUser.getId(), currentTwitterUser.getId(), currentTwitterUser.getName(), 10, SharedPreferencesHelper.getAuthenticationToken(FollowerUserProfileActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowerUserProfileActivity.this));

    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(com.twitter.sdk.android.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );
    }

    private void showSelectedUserTweets(long followerID, long userID, String username, final long count, String authToken, String authSecret) {
//         GeneralMethods.printLog("ddd",result.data.getUserName()+"-"+ result.data.getUserId());
//        swipeRefreshLayoutFollowersList.setRefreshing(true);
        TwitterAuthToken twitterAuthToken = new TwitterAuthToken(authToken, authSecret);
        TwitterSession session = new TwitterSession(twitterAuthToken, userID, username);

        UserTweetsApiClient twitterFollowersApiClient = new UserTweetsApiClient(session);
        twitterFollowersApiClient.getCustomService().TweetsList(followerID, count, false, true, new Callback<Response>() {

            @Override
            public void failure(TwitterException arg0) {
                // TODO Auto-generated method stub
                GeneralMethods.printLog("====failure", arg0.getMessage());
                Toast.makeText(FollowerUserProfileActivity.this, arg0.getMessage(), Toast.LENGTH_LONG).show();
//                swipeRefreshLayoutFollowersList.setRefreshing(false);
//                progressIndecator.setVisibility(View.GONE);
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
                GeneralMethods.printLog("=====Results", result);
//                applyFollowersParsersAndFillList(result, cursor);
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

}
