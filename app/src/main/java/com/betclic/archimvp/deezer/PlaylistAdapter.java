package com.betclic.archimvp.deezer;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class PlaylistAdapter {
/*
    @FromJson
    Playlist fromJson(String playlistJson) {
        Moshi moshi = new Moshi.Builder().build();
        try {
            JSONObject jsonObject = new JSONObject(playlistJson);
            String accountType = jsonObject.getString("type");

            switch (accountType) {
                case "Child":
                    JsonAdapter<Child> childJsonAdapter = moshi.adapter(Child.class);
                    return childJsonAdapter.fromJson(userJson);
                case "Parent":
                    JsonAdapter<Parent> parentJsonAdapter = moshi.adapter(Parent.class);
                    return parentJsonAdapter.fromJson(userJson);

            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @ToJson
    String toJson(Playlist playlist) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<User> jsonAdapter = moshi.adapter(User.class);
        String toJson = jsonAdapter.toJson(user);
        return toJson;
    }*/

}
