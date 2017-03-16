package com.betclic.archimvp.user;

import io.reactivex.Observable;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class UserManager {

    private final UserAPIClient client;
    private final UserDAO dao;

    public UserManager(UserDAO dao, UserAPIClient client) {
        this.dao = dao;
        this.client = client;
    }

    public String getUserName() {
        return "COUCOU";
    }

}
