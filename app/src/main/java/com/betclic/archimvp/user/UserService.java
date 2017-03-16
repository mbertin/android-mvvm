package com.betclic.archimvp.user;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.Query;

import static com.betclic.archimvp.core.network.BetclicService.HEADER_AUTHORIZATION;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public interface UserService {

    String FIELD_LOGIN = "login";
    String FIELD_PASSWORD = "password";

    Observable<User> login(@Header(HEADER_AUTHORIZATION) String token,
                           @Query(FIELD_LOGIN) String login,
                           @Query(FIELD_PASSWORD) String password
    );
}
