package com.eventtus.simpletwitterclient.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.eventtus.simpletwitterclient.Models.TwitterUser;
import com.eventtus.simpletwitterclient.Models.UserTimeline;
import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.controls.Parser;
import com.eventtus.simpletwitterclient.controls.adapters.UsersTweetsAdapter;
import com.eventtus.simpletwitterclient.controls.twitter.UserTweetsApiClient;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;
import com.eventtus.simpletwitterclient.helpers.RoundedCornersTransformation;
import com.eventtus.simpletwitterclient.helpers.SharedPreferencesHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit.client.Response;

/**
 * Created by Bassam on 6/22/2016.
 */
public class FollowerUserProfileActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    public static final String SELECTED_USER_FLAG = "SELECTED_USER_FLAG";
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private TwitterUser currentFollowedUser;
    private TwitterUser currentTwitterUser;
    private Toolbar toolbar;
    private ImageView imgVUserCover;
    private RecyclerView lstTweets;
    private UsersTweetsAdapter twitterFollowersUsersAdapter;
    private boolean mIsTheTitleContainerVisible = true;
    private ImageView imgProfile;
    private AppBarLayout appBarLayoutUserProfile;
    private View circularProgressLoadingFirstTime;
    private CoordinatorLayout coordinateLayoutUserProfile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        imgVUserCover = (ImageView) findViewById(R.id.imgv_user_cover);
        currentFollowedUser = (TwitterUser) getIntent().getSerializableExtra(SELECTED_USER_FLAG);
        coordinateLayoutUserProfile = (CoordinatorLayout) findViewById(R.id.coordinate_layout_user_profile);

        Picasso.with(FollowerUserProfileActivity.this).load(currentFollowedUser.getProfileBannerUrl()).into(imgVUserCover);
        GeneralMethods.printLog("====Current user", currentFollowedUser.getName());
        currentTwitterUser = SharedPreferencesHelper.getSavedTwitterUserFrom(FollowerUserProfileActivity.this);
        initToolBar(currentFollowedUser.getName());
        initImageProfile();
        initLoadingFirstTime();
        initTweetsRecycleList();
        showSelectedUserTweets(currentFollowedUser.getId(), currentTwitterUser.getId(), currentTwitterUser.getName(), 10, SharedPreferencesHelper.getAuthenticationToken(FollowerUserProfileActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowerUserProfileActivity.this));

    }

    private void initLoadingFirstTime() {
        circularProgressLoadingFirstTime = findViewById(R.id.progress_first_time_loading);
        circularProgressLoadingFirstTime.setVisibility(View.VISIBLE);
    }

    private void initImageProfile() {
        imgProfile = (ImageView) findViewById(R.id.imgv_user_Profile);
        final int radius = 15;
        final int margin = 10;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.with(FollowerUserProfileActivity.this).load(currentFollowedUser.getProfileImageUrl()).transform(transformation).placeholder(R.drawable.ic_profile_dummy).into(imgProfile);
    }

    public void initToolBar(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar_followers_list);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(com.twitter.sdk.android.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );
        appBarLayoutUserProfile = (AppBarLayout) findViewById(R.id.app_bar_layout_user_profile);
        appBarLayoutUserProfile.addOnOffsetChangedListener(this);

    }

    private void initTweetsRecycleList() {
        lstTweets = (RecyclerView) findViewById(R.id.list_tweets);
        lstTweets.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lstTweets.setLayoutManager(llm);


    }

    private void showSelectedUserTweets(long followerID, long userID, String username, final long count, String authToken, String authSecret) {
        if(!GeneralMethods.isNetworkAvailable(FollowerUserProfileActivity.this)){
            ShowConnectionErrorSnackBar(getString(R.string.no_internet_connection_error_message));
            return;
        }
        TwitterAuthToken twitterAuthToken = new TwitterAuthToken(authToken, authSecret);
        TwitterSession session = new TwitterSession(twitterAuthToken, userID, username);

        UserTweetsApiClient twitterFollowersApiClient = new UserTweetsApiClient(session);
        twitterFollowersApiClient.getCustomService().TweetsList(followerID, count, false, true, new Callback<Response>() {

            @Override
            public void failure(TwitterException arg0) {
                // TODO Auto-generated method stub
                GeneralMethods.printLog("====failure", arg0.getMessage());
                Toast.makeText(FollowerUserProfileActivity.this, arg0.getMessage(), Toast.LENGTH_LONG).show();
                circularProgressLoadingFirstTime.setVisibility(View.GONE);
                ShowConnectionErrorSnackBar(getString(R.string.connection_error_message));
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
                ArrayList<UserTimeline> allUserTimeline = new Parser().parseStatusesTimeLine(result);
                fillTweetsList(allUserTimeline);
                for (int i = 0; i < allUserTimeline.size(); i++) {
                    GeneralMethods.printLog("=====text", allUserTimeline.get(i).getText());
                    GeneralMethods.printLog("=====user", allUserTimeline.get(i).getCurrentTwitterUser().getName());
                    GeneralMethods.printLog("=====image", allUserTimeline.get(i).getCurrentTwitterUser().getProfileImageUrl());
                }

            }

        });
    }



    private void fillTweetsList(ArrayList<UserTimeline> allUserTimeLine) {
        twitterFollowersUsersAdapter = new UsersTweetsAdapter(FollowerUserProfileActivity.this, allUserTimeLine);
        lstTweets.setAdapter(twitterFollowersUsersAdapter);
        circularProgressLoadingFirstTime.setVisibility(View.GONE);
    }

    public void startAlphaAnimation(final View v, long duration, final int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (visibility == View.VISIBLE) {
                    v.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(alphaAnimation);

    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {

        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
//        handleToolbarTitleVisibility(percentage);
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(imgProfile, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(imgProfile, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }
    public void ShowConnectionErrorSnackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(coordinateLayoutUserProfile, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry_label, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSelectedUserTweets(currentFollowedUser.getId(), currentTwitterUser.getId(), currentTwitterUser.getName(), 10, SharedPreferencesHelper.getAuthenticationToken(FollowerUserProfileActivity.this), SharedPreferencesHelper.getAuthenticationSecret(FollowerUserProfileActivity.this));
                    }
                });

        snackbar.show();
    }
}
