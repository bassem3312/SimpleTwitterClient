package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.User;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Bassem on 11/15/2015.
 */
public interface UsersService {
    @GET("/1.1/users/show.json")
    public void show(@Query("user_id") Long userId,
                     @Query("screen_name") String screenName,
                     @Query("include_entities") Boolean includeEntities,
                     Callback<User> cb);
}