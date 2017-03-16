package com.betclic.archimvp.deezer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Singleton
public class DeezerManager {

    private final DeezerApiClient mApiClient;
    private final DeezerDao mDao;

    private final BehaviorSubject<LiveData<List<Playlist>>> playlistsSubject;

    private Disposable pollingObservable;

    @Inject
    public DeezerManager(DeezerApiClient apiClient) {
        mApiClient = apiClient;
        mDao = new DeezerDao();

        playlistsSubject = BehaviorSubject.create();

        mDao.getPlaylists()
                .subscribeOn(Schedulers.io())
                .subscribe(playlists -> {
                    playlistsSubject.onNext(new LiveData<>(playlists, false));
                });

       initPolling();
    }

    public BehaviorSubject<LiveData<List<Playlist>>> getPlaylists() {
        return playlistsSubject;
    }

    public void forceFetchPlaylist() {
        pollingObservable.dispose();
        fetchPlaylists();
    }


    private void fetchPlaylists() {

        if(playlistsSubject.hasObservers()) {

            mApiClient.getPlaylists()
                    .subscribe(playlists -> {
                            mDao.savePlaylist(playlists); // TO keep or not ????

                            LiveData<List<Playlist>> result = new LiveData<>(playlists, true);
                            playlistsSubject.onNext(result);

                            initPolling();
                        },
                        error -> {
                            Timber.e(error);
                            playlistsSubject.onNext(new LiveData<>(playlistsSubject.getValue().value, false));
                        }
                    );
        }
    }

    private void initPolling() {
        if(pollingObservable == null || pollingObservable.isDisposed()) {
            pollingObservable = Observable.interval(5, TimeUnit.SECONDS)
                    .observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .subscribe(time->
                            fetchPlaylists()
                    );
        }
    }

}
