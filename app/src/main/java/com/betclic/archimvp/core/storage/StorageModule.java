package com.betclic.archimvp.core.storage;

import android.content.Context;

import java.util.UUID;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Singleton
@Module
public class StorageModule {

    private final Context mContext;

    public StorageModule(Context ctx) {
        mContext = ctx;
    }

    @Provides
    SharedPreferencesManager provideSharedPreferencesManager() {
        return new SharedPreferencesManager(mContext);
    }

/*    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }
*/
}
