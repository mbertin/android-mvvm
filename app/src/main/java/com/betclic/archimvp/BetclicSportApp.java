package com.betclic.archimvp;

import com.betclic.archimvp.core.android.BetclicApplication;
import com.betclic.archimvp.core.network.NetworkModule;
import com.betclic.archimvp.core.storage.StorageModule;
import com.betclic.archimvp.user.UserComponent;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class BetclicSportApp extends BetclicApplication {

    UserComponent mUserComponent;

    private BetclicSportComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration myConfig = new RealmConfiguration.Builder()
                .name(UUID.randomUUID().toString())
                .inMemory()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(myConfig);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerBetclicSportComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public UserComponent getUserComponent() {
        return this.mUserComponent;
    }

    public BetclicSportComponent getAppComponent() {
        return mAppComponent;
    }
}
