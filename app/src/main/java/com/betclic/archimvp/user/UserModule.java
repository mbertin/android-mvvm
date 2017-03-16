package com.betclic.archimvp.user;

import com.betclic.archimvp.core.auth.AuthManager;
import com.betclic.archimvp.core.storage.SharedPreferencesManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Module
public class UserModule {
    @Singleton
    @Provides
    UserDAO provideUserDAO(Realm realm, SharedPreferencesManager sharedPreferencesManager) {
        return new UserDAO(realm, sharedPreferencesManager);
    }

    @Singleton
    @Provides
    UserAPIClient provideUserApiClient(@Named("globalRetrofit") Retrofit retrofit, AuthManager authManager) {
        return new UserAPIClient(retrofit, authManager);
    }

    @Singleton
    @Provides
    UserManager provideUserManager(UserDAO dao, UserAPIClient client) {
        return new UserManager(dao, client);
    }
}
