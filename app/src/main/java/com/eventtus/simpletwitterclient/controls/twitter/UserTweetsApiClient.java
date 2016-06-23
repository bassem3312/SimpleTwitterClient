package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Bassam on 6/20/2016.
 */
public class UserTweetsApiClient extends TwitterApiClient {
    public UserTweetsApiClient(TwitterSession session) {
        super(session);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public TweetsListService getCustomService() {
        return getService(TweetsListService.class);
    }
}

