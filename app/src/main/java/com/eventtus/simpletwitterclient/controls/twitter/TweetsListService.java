package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.Callback;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Bassam on 6/20/2016.
 */
public interface TweetsListService {
    @GET("/1.1/statuses/user_timeline.json")
    void TweetsList(@Query("user_id") long id, @Query("count") long count, @Query("include_rts") boolean includeRts,@Query("exclude_replies") boolean excludeReplies, Callback<Response> cb);
}
