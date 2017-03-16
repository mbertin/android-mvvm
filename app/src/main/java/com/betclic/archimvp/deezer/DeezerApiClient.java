package com.betclic.archimvp.deezer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.realm.RealmList;
import retrofit2.Retrofit;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Singleton
public class DeezerApiClient {

    private final DeezerService mService;

    @Inject
    public DeezerApiClient(@Named("deezerRetrofit") Retrofit retrofit) {
        mService = retrofit.create(DeezerService.class);
    }

    Single<List<Playlist>> getPlaylists() {
        return mService.getPlaylists()
                .map(playlistsResponse -> playlistsResponse.data)
                .flatMap(playlists -> Observable.fromIterable(playlists))
                .flatMap(playlist ->
                        mService.getPlaylistTracks(playlist.getTracklist())
                            .flatMap(tracksResponse -> {
                                RealmList<Track> tracks = new RealmList<Track>();
                                tracks.addAll(tracksResponse.data);
                                playlist.setTracks(tracks);

                                return Observable.just(playlist);
                            }))
                .toList();
    }

}
