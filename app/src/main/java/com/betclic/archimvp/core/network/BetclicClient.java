package com.betclic.archimvp.core.network;

import com.betclic.archimvp.core.auth.AuthManager;

import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class BetclicClient {

    protected AuthManager mAuthManager;

    public BetclicClient(Retrofit retrofit, AuthManager authManager) {
        Timber.d("Client created with url: %s", retrofit.baseUrl().toString());

        this.mAuthManager = authManager;
    }

}
