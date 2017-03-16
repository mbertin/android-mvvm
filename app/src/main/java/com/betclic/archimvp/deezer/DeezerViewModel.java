package com.betclic.archimvp.deezer;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Mathieu Bertin on 15/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class DeezerViewModel {

    private final DeezerManager mDeezerManager;
    private final BehaviorSubject<LiveData<List<Playlist>>> mPlaylistSubject;

    private int mPlaylistsSize;
    private String mTextFilter;

    @Inject
    public DeezerViewModel(DeezerManager manager) {
        mDeezerManager = manager;
        mPlaylistSubject = mDeezerManager.getPlaylists();
    }

    public void subscribe(Observable<ActivityEvent> activityLifecycle, Observer<LiveData<List<String>>> consumer, InitialValueObservable<CharSequence> filterText) {

        Observable.combineLatest(mPlaylistSubject, filterText,
            (playlists, text) -> {
                mTextFilter = text.toString();
                return playlists;
            })
            .flatMap( data ->  {
                mPlaylistsSize = data.value.size();

                if (mPlaylistsSize > 0) {
                    return Observable.fromIterable(data.value)
                            .map(playlist -> String.format("%s (%d)",  playlist.getTitle(), playlist.getTracks().size()))
                            .filter(title -> title.contains(mTextFilter))
                            .buffer(mPlaylistsSize)
                            .map(playlistsTitle -> new LiveData<>(playlistsTitle, data.isValid));
                }
                else {
                    return Observable.just(new LiveData<List<String>>(new ArrayList<>(), data.isValid));
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycle.bindUntilEvent(activityLifecycle, ActivityEvent.STOP))
            .subscribe(consumer);
    }



}
