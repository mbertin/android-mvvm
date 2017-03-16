package com.betclic.archimvp.core.auth;

import android.content.SharedPreferences;

import com.betclic.archimvp.core.storage.SharedPreferencesManager;

import io.reactivex.Single;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class AuthDAO {

    private SharedPreferences mSharedPrefs;
    private SharedPreferences.Editor mSharedPrefsEditor;

    private static final String TOKEN_KEY = "token";

    public AuthDAO(SharedPreferencesManager sharedPreferencesManager) {
        this.mSharedPrefs = sharedPreferencesManager.getReader();
        this.mSharedPrefsEditor = sharedPreferencesManager.getEditor();
    }


    public Single<AuthToken> getAuthToken() {
        return Single.create(singlePublisher -> {

            AuthToken token = null;
            String storedTokenString = mSharedPrefs.getString(TOKEN_KEY, null);
            if(storedTokenString != null) {
                token = new AuthToken(storedTokenString);
            }

            singlePublisher.onSuccess(token);
        });
    }

    public Single<AuthToken> saveAuthToken(AuthToken token) {
        return Single.create(singlePublisher -> {
            mSharedPrefsEditor.putString(TOKEN_KEY, token.toString());
            mSharedPrefsEditor.apply();
            singlePublisher.onSuccess(token);
        });
    }
}
