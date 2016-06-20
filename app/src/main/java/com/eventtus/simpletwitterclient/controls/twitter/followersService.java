package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.Callback;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Bassam on 6/20/2016.
 */
public interface FollowersService {
    @GET("/1.1/followers/list.json")
    void list(@Query("user_id") long id, @Query("cursor") long cursor, Callback<Response> cb);
}
