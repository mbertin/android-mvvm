package com.betclic.archimvp.user;

import com.betclic.archimvp.core.storage.SharedPreferencesManager;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmQuery;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class UserDAO {

    private final Realm realmInstance;

    public UserDAO(Realm realm, SharedPreferencesManager sharedPreferencesManager) {
        this.realmInstance = realm;
    }

}
