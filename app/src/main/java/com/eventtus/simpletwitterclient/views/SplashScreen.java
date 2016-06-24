package com.eventtus.simpletwitterclient.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.eventtus.simpletwitterclient.R;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;
import com.eventtus.simpletwitterclient.helpers.SharedPreferencesHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Bassam on 6/20/2016.
 */
public class SplashScreen extends Activity {
    private long splashTimerDelay = 4000;
    private Timer splashTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                openNextScreen();
            }
        }, splashTimerDelay);
    }

    private void openNextScreen() {
        if (SharedPreferencesHelper.isLoggedIN(SplashScreen.this)) {
            GeneralMethods.openFollowersActivity(SplashScreen.this);
        } else {
            Intent nextIntent = new Intent(SplashScreen.this, LoginActivity.class);
            // set the new task and clear flags
            nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(nextIntent);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            splashTimer.cancel();
        } catch (Exception ex) {
        }
    }
}
