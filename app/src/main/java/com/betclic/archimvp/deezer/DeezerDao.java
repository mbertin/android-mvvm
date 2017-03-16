package com.betclic.archimvp.deezer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
class DeezerDao {

    public void savePlaylist(List<Playlist> playlists) {

        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.beginTransaction();
        realmInstance.copyToRealmOrUpdate(playlists);
        realmInstance.commitTransaction();
        realmInstance.close();

        Timber.d("%d playlists saved", playlists.size());
    }

    public Observable<List<Playlist> > getPlaylists() {

        return Observable.create( source -> {
            Realm realmInstance = Realm.getDefaultInstance();
            RealmResults<Playlist> result = realmInstance.where(Playlist.class).findAll();

            List<Playlist> playlists = new ArrayList<>();
            playlists.addAll(realmInstance.copyFromRealm(result));

            realmInstance.close();

            source.onNext(playlists);
            source.onComplete();
        });
    }
}
