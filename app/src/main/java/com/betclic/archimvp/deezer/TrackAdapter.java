package com.betclic.archimvp.deezer;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.Collection;

import io.realm.RealmList;

/**
 * Created by Mathieu Bertin on 15/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class TrackAdapter {

    @ToJson
    public Collection<Track> toJson(RealmList<Track> realmList) {
        return realmList;
    }



    @FromJson
    public RealmList<Track> fromJson(Collection<Track> collection) {
        RealmList<Track> realmList = new RealmList<Track>();
        realmList.addAll(collection);
        return realmList;
    }
}
