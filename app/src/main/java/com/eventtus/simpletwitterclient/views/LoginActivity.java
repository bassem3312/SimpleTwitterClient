package com.eventtus.simpletwitterclient.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.controls.twitter.TwitterUserApiClient;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;
import com.eventtus.simpletwitterclient.helpers.SharedPreferencesHelper;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {
    private TwitterLoginButton loginButton;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_consumer_key), getString(R.string.twitter_consumer_secret));
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_login);
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                //save twitter authentication into shared preferences.
                SharedPreferencesHelper.putTwitterAuthenticationKeys(LoginActivity.this, token, secret);

                //Read UserEmail and after it read user data and save it into shared preferences.
                readTwitterUserEmail(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

    /**
     * Read Twitter TwitterUser Email and then call read user data
     *
     * @param resultSession Twitter user session
     */
    private void readTwitterUserEmail(TwitterSession resultSession) {
        final TwitterSession sessionData = resultSession;
        TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        twitterAuthClient.requestEmail(sessionData, new Callback<String>() {
                    @Override
                    public void success(final Result<String> result) {
                        final String userEmail = result.data.toString();
                        readTwitterUserData(sessionData, userEmail);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        GeneralMethods.printLog("twitter community", "exception is " + exception);
                        readTwitterUserData(sessionData, "");
                    }
                }

        );

    }

    /**
     *  retrieve user data and then call save user data into shared preferences
     * @param sessionData session data that should we use to retrieve user data
     * @param userEmail user email address.
     */
    private void readTwitterUserData(TwitterSession sessionData, final String userEmail) {
        new TwitterUserApiClient(sessionData).getUsersService().show(sessionData.getUserId(), sessionData.getUserName(), true,
                new Callback<User>() {
                    @Override
                    public void success(Result<User> result) {
                        GeneralMethods.printLog("twitter community", "user's profile url is " + result.data.profileImageUrlHttps);
                        saveTwitterUserData(result.data, userEmail);
                    }


                    @Override
                    public void failure(TwitterException exception) {
                        GeneralMethods.printLog("twitter community", "exception is " + exception);
                    }
                });
    }


    /**
     *
     * @param currentUser twitter user object
     * @param userEmail user email
     */
    private void saveTwitterUserData(User currentUser, String userEmail) {
        long userID = currentUser.getId();
        String userName = currentUser.screenName;
        String fullName = currentUser.name;
        String bio = currentUser.description;
        String profileImageURL = currentUser.profileImageUrl.replace("_normal", "");
        String backgroundImageURL = currentUser.profileBannerUrl + "/mobile_retina";
        if (userEmail == null || userEmail.isEmpty()) {
            userEmail = userName + "@twitter.com";
        }
        //Save user data into shared preferences
        SharedPreferencesHelper.putUserData(LoginActivity.this, userID, userEmail, fullName, userName, bio, profileImageURL, backgroundImageURL, true);
        GeneralMethods.openFollowersActivity(LoginActivity.this);
        LoginActivity.this.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    ///////////////////
}
