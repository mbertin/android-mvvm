package com.betclic.archimvp.core.android;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class BetclicApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        /*
        NetworkComponent networkComponent = DaggerNetworkComponent.builder()

                .build();

        StorageComponent storageComponent = DaggerStorageComponent.builder()

                .build();
        */
    }
}
