package com.eventtus.simpletwitterclient.helpers;

import android.util.Log;

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
}
