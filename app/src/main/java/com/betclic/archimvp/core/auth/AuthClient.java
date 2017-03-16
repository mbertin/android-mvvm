package com.betclic.archimvp.core.auth;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class AuthClient {

    private AuthService mAuthService;

    public AuthClient(Retrofit retrofit) {
        this.mAuthService = retrofit.create(AuthService.class);
    }

    public Single<AuthToken> getAuthToken() {
        return mAuthService.getVisitorToken();
    }
}
