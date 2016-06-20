package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Bassam on 6/20/2016.
 */
public class TwitterFollowersApiClient extends TwitterApiClient {
    public TwitterFollowersApiClient(TwitterSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public FollowersService getCustomService() {
        return getService(FollowersService.class);
    }
}

