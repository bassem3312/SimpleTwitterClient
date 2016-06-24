package com.eventtus.simpletwitterclient.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.eventtus.simpletwitterclient.views.FollowersActivity;

/**
 * Created by Bassam on 6/20/2016.
 */
public class GeneralMethods {
    public static void printLog(String TAG, String message) {
        try {
            Log.e("Bassem", TAG + " " + message);
        } catch (Exception ex) {
        }
    }

    /**
     * Start followers Activity.
     *
     * @param context
     */
    public static void openFollowersActivity(Activity context) {
        Intent nextIntent = new Intent(context, FollowersActivity.class);
        // set the new task and clear flags
//        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(nextIntent);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
