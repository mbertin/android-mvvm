package com.betclic.archimvp.core.auth;

import com.betclic.archimvp.core.storage.SharedPreferencesManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Module
public class AuthModule {

    @Singleton
    @Provides
    AuthClient provideAuthClient(@Named("globalRetrofit") Retrofit retrofit) {
        return new AuthClient(retrofit);
    }

    @Singleton
    @Provides
    AuthDAO provideAuthDAO(SharedPreferencesManager sharedPreferencesManager) {
        return new AuthDAO(sharedPreferencesManager);
    }

    @Singleton
    @Provides
    AuthManager provideAuthManager(AuthClient authClient, AuthDAO dao) {
       return new AuthManager(authClient, dao);
    }
}
