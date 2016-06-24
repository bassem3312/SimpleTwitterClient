package com.eventtus.simpletwitterclient.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.controls.twitter.TwitterFollowersApiClient;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.fabric.sdk.android.Fabric;
import retrofit.client.Response;

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
                // Do something with result, which provides a TwitterSession for making API calls
                GeneralMethods.printLog("TwitterUser Name", result.data.getUserName());
                GeneralMethods.printLog("TwitterUser ID", result.data.getUserId() + "");

                // get session authToken/authSecret Keys and save them in shared preferences.
                TwitterSession session = Twitter.getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                GeneralMethods.printLog("Twitter Token", token);
                GeneralMethods.printLog("Twitter Secret", secret);
                SharedPreferencesHelper.putTwitterAuthenticationKeys(LoginActivity.this, token, secret);

                //Read UserEmail and after it read user data and save it into shared preferences.
                readTwitterUserEmail(result.data);

//                showFollowers(result.data.getUserId(),result.data.getUserName(),token,secret);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

    /**
     * Read Twitter TwitterUser Email
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

    private void saveTwitterUserData(User currentUser, String useremail) {
        long userID = currentUser.getId();
        String userName = currentUser.screenName;
        String fullName = currentUser.name;
        String bio = currentUser.description;
        GeneralMethods.printLog("=====", currentUser.profileImageUrl);
        String profileImageURL = currentUser.profileImageUrl.replace("_normal", "");
        String backgroundImageURL = currentUser.profileBannerUrl + "/mobile_retina";
        if (useremail == null || useremail.isEmpty()) {
            useremail = userName + "@twitter.com";
        }

        GeneralMethods.printLog("TwitterUser ID", userID + "");
        GeneralMethods.printLog("userName", userName);
        GeneralMethods.printLog("fullName", fullName);
        GeneralMethods.printLog("bio", bio);
        GeneralMethods.printLog("email", useremail);
        GeneralMethods.printLog("Profile Image URL", profileImageURL);
        GeneralMethods.printLog("banner Image https", backgroundImageURL);
        SharedPreferencesHelper.putUserData(LoginActivity.this, userID, useremail, fullName, userName, bio, profileImageURL, backgroundImageURL, true);
        GeneralMethods.openFollowersActivity(LoginActivity.this);
    }

    private void showFollowers(long userID, String username, String authToken, String authSecret) {
//         GeneralMethods.printLog("ddd",result.data.getUserName()+"-"+ result.data.getUserId());
        TwitterAuthToken twitterAuthToken = new TwitterAuthToken(authToken, authSecret);
        TwitterSession session = new TwitterSession(twitterAuthToken, userID, username);

        Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {

                    @Override
                    public void success(Result<User> userResult) {

                        User user = userResult.data;
//Here we get image url which can be used to set as image wherever required.
                        GeneralMethods.printLog("ddd", user.profileImageUrl + " " + user.email + "" + user.followersCount);

                    }

                    @Override
                    public void failure(TwitterException e) {

                    }

                });
//        shared.edit().putString("tweetToken", token).commit();
//        shared.edit().putString("tweetSecret", secret).commit();
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the
                // email address
                GeneralMethods.printLog("ddd=====", result.toString());
                GeneralMethods.printLog("Result", result.toString());
                Toast.makeText(getApplicationContext(), result.data,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                GeneralMethods.printLog("dddyyyy", exception.getMessage());
            }
        });
        TwitterFollowersApiClient apiclients = new TwitterFollowersApiClient(session);
        apiclients.getCustomService().list(userID, 0, new Callback<Response>() {

            @Override
            public void failure(TwitterException arg0) {
                // TODO Auto-generated method stub

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
                GeneralMethods.printLog("Response is>>>>>>>>>", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray ids = obj.getJSONArray("users");
                    //This is where we get ids of followers
                    for (int i = 0; i < ids.length(); i++) {
                        GeneralMethods.printLog("ddd", "Id of user " + (i + 1) + " is " + ids.getJSONObject(i).getString("name"));
                    }
                    String nextCursor = obj.getString("next_cursor_str");
                    GeneralMethods.printLog("====next_cursor_str", nextCursor);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    ///////////////////
}
