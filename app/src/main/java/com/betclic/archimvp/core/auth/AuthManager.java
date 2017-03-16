package com.betclic.archimvp.core.auth;

import io.reactivex.Single;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class AuthManager {

    private final AuthDAO mDao;
    private final AuthClient mAuthClient;

    public AuthManager(AuthClient authClient, AuthDAO authDao) {
        this.mAuthClient = authClient;
        this.mDao = authDao;
    }

    public Single<AuthToken> getAuthToken() {
        return mDao.getAuthToken()
                .flatMap(authToken -> {
                    if (authToken != null) {
                        return Single.just(authToken);
                    }
                    else {
                        return mAuthClient.getAuthToken()
                                .flatMap(mDao::saveAuthToken);
                    }
                });
    }


}
