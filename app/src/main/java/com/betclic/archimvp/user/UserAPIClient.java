package com.betclic.archimvp.user;

import com.betclic.archimvp.core.auth.AuthManager;
import com.betclic.archimvp.core.network.BetclicClient;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class UserAPIClient extends BetclicClient{

    private final UserService userService;

    public UserAPIClient(Retrofit retrofit, AuthManager authManager) {
        super(retrofit, authManager);
        this.userService = retrofit.create(UserService.class);
    }

    public Observable<User> loginUser() {
        return this.userService.login("ë", "fsd", "as");
    }
}
