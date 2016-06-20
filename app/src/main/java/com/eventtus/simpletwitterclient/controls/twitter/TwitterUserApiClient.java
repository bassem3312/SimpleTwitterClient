package com.eventtus.simpletwitterclient.controls.twitter;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by Bassem on 11/15/2015.
 */
public class TwitterUserApiClient extends TwitterApiClient {
    public TwitterUserApiClient(TwitterSession session) {
        super(session);
    }

    public UsersService getUsersService() {
        return getService(UsersService.class);
    }
}


