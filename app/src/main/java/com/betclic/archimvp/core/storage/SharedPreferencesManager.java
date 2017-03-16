package com.betclic.archimvp.core.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class SharedPreferencesManager {

    private final static String SHARED_PREFERENCES_NAME = "Betclic";


    private SharedPreferences mReader;
    private SharedPreferences.Editor mEditor;

    public SharedPreferencesManager(Context mContext) {
        this.mReader = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        this.mEditor = this.mReader.edit();
    }

    public SharedPreferences getReader() {
        return mReader;
    }

    public SharedPreferences.Editor getEditor() {
        return mEditor;
    }
}
