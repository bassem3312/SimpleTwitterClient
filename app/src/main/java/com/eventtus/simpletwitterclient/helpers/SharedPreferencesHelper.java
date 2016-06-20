package com.eventtus.simpletwitterclient.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.eventtus.simpletwitterclient.Models.TwitterUser;

public class SharedPreferencesHelper {
    private final static String CREDENTIAL_PREFERENCE_NAME = "TWITTER_CREDENTIAL";
    private final static String IS_LOGEDIN_FLAG = "LOGIN";
    private final static String EMAIL_FLAG = "USER_EMAIL";
    private final static String SHARED_PREF_DEFAULT_ = "DEAFULT";
    private static final String USER_ID_FLAG = "USER_ID_FLAG";
    private static final String USER_NAME_FLAG = "USER_NAME_FLAG";
    private static final String USER_PROFILE_IMAGE_URL_FLAG = "USER_PROFILE_IMAGE_URL_FLAG";
    private static final String USER_BACKGROUND_IMAGE_URL_FLAG = "USER_BACKGROUND_IMAGE_URL_FLAG";
    private static final String AUTH_TOKEN_FLAG = "AUTH_TOKEN_FLAG";
    private static final String AUTH_SECRET_FLAG = "AUTH_SECRET_FLAG";


    /**
     * save twitter user data had been retrieved from twitter sdk into shared preferences after twitter login.
     *
     * @param context
     * @param userID
     * @param email
     * @param username
     * @param isLoggedIN
     * @param userProfileImageURL
     * @param userBackgroundImageURL
     */
    public static void putUserData(Activity context, long userID, String email, String username, boolean isLoggedIN, String userProfileImageURL, String userBackgroundImageURL) {
        SharedPreferences sp = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(IS_LOGEDIN_FLAG, isLoggedIN);
        editor.putLong(USER_ID_FLAG, userID);
        if (username != null && !username.isEmpty()) {
            editor.putString(USER_NAME_FLAG, username);
        }
        if (userProfileImageURL != null && !userProfileImageURL.isEmpty()) {
            editor.putString(USER_PROFILE_IMAGE_URL_FLAG, userProfileImageURL);
        }
        if (userBackgroundImageURL != null && !userBackgroundImageURL.isEmpty()) {
            editor.putString(USER_BACKGROUND_IMAGE_URL_FLAG, userBackgroundImageURL);
        }
        if (email != null && !email.isEmpty()) {
            editor.putString(EMAIL_FLAG, email);
        }
        editor.commit();
    }

    /**
     * save authentications token and secret keys to shared preferences.
     *
     * @param context    Application Context.
     * @param authToken  Authentication Token key that retrieved from twitter authentication
     * @param authSecret Authentication secret key that retrieved from twitter authentication
     */

    public static void putTwitterAuthenticationKeys(Activity context, String authToken, String authSecret) {
        SharedPreferences sp = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (authToken != null && !authToken.isEmpty()) {
            editor.putString(AUTH_TOKEN_FLAG, authToken);
        }
        if (authSecret != null && !authSecret.isEmpty()) {
            editor.putString(AUTH_SECRET_FLAG, authSecret);
        }
        editor.commit();
    }


    /**
     * get user status if logged-in on our application or not.
     */
    public static boolean isLoggedIN(Context context) {
        SharedPreferences spUser = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return spUser.getBoolean(IS_LOGEDIN_FLAG, false);
//        return false;
    }

    public static TwitterUser getSavedTwitterUserFrom(Activity context) {
        if (!isLoggedIN(context)) {
            return null;
        }
        SharedPreferences spUser = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        TwitterUser savedTwitterUser = new TwitterUser();
        savedTwitterUser.setUserID(spUser.getLong(USER_NAME_FLAG, -1));
        savedTwitterUser.setUsername(spUser.getString(USER_NAME_FLAG, ""));
        savedTwitterUser.setEmail(spUser.getString(EMAIL_FLAG, ""));
        savedTwitterUser.setProfileImageURL(spUser.getString(USER_PROFILE_IMAGE_URL_FLAG, ""));
        savedTwitterUser.setBackgroundImageURL(spUser.getString(USER_BACKGROUND_IMAGE_URL_FLAG, ""));
        return savedTwitterUser;
    }

    /**
     *
     * @param context
     * @return Twitter Authentication Token Key.
     */
    public static String getAuthenticationToken(Context context) {
        SharedPreferences spUser = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return spUser.getString(AUTH_TOKEN_FLAG, "");
    }

    /**
     *
     * @param context
     * @return Twitter Authentication Secret Key.
     */
    public static String getAuthenticationSecret(Context context) {
        SharedPreferences spUser = context.getSharedPreferences(CREDENTIAL_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return spUser.getString(AUTH_SECRET_FLAG, "");
    }
}
