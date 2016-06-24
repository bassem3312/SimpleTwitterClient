package com.eventtus.simpletwitterclient.controls;

import com.eventtus.simpletwitterclient.Models.TwitterUser;
import com.eventtus.simpletwitterclient.Models.UserTimeline;
import com.eventtus.simpletwitterclient.helpers.GeneralMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Bassam on 6/23/2016.
 */
public class Parser {
    public ArrayList<UserTimeline> parseStatusesTimeLine(String result) {
        GeneralMethods.printLog("Response is>>>>>>>>>", result);
        ArrayList<UserTimeline> allTweets = new ArrayList<>();
        try {
            JSONArray tweetsArray = new JSONArray(result);
            UserTimeline currentUserTimeline;
            for (int i = 0; i < tweetsArray.length(); i++) {
                JSONObject currentTweetJsonObject = tweetsArray.getJSONObject(i);
                currentUserTimeline = new UserTimeline();
                currentUserTimeline.setId(currentTweetJsonObject.getLong("id"));
                currentUserTimeline.setRetweet_count(currentTweetJsonObject.getLong("retweet_count"));
                currentUserTimeline.setText(currentTweetJsonObject.getString("text"));
                currentUserTimeline.setCreatedAt(currentTweetJsonObject.getString("created_at"));

                JSONObject currentUserJsonObject = currentTweetJsonObject.getJSONObject("user");
                TwitterUser currentUser = new TwitterUser();
                currentUser.setName(currentUserJsonObject.getString("name"));
                currentUser.setScreenName(currentUserJsonObject.getString("screen_name"));
                currentUser.setProfileImageUrl(currentUserJsonObject.getString("profile_image_url"));

                currentUserTimeline.setCurrentTwitterUser(currentUser);
                allTweets.add(currentUserTimeline);
            }
            return allTweets;

//            JSONArray ids = obj.getJSONArray("users");
//            //This is where we get ids of followers
//            for (int i = 0; i < ids.length(); i++) {
//                GeneralMethods.printLog("ddd", "Id of user " + (i + 1) + " is " + ids.getJSONObject(i).getString("name"));
//            }
//            String nextCursor = obj.getString("next_cursor_str");
//            GeneralMethods.printLog("====next_cursor_str", nextCursor);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
