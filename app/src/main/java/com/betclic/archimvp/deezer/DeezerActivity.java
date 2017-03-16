package com.betclic.archimvp.deezer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.betclic.archimvp.BetclicSportApp;
import com.betclic.archimvp.R;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public class DeezerActivity extends RxAppCompatActivity implements Observer<LiveData<List<String>>>{


    @Inject DeezerViewModel mDeezerViewModel;

    @BindView(R.id.playlists_title) RecyclerView mRecyclerView;

    @BindView(R.id.filter) EditText mEtFilter;
    @BindView(R.id.isValid) TextView mTvIsValid;


    private DeezerPlaylistAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deezer);

        ButterKnife.bind(this);

        ((BetclicSportApp)getApplication()).getAppComponent().inject(this);

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPlaylistsObserver();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DeezerPlaylistAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void initPlaylistsObserver() {
                mDeezerViewModel
                .subscribe(this.lifecycle(), this, RxTextView.textChanges(mEtFilter));
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(LiveData<List<String>> liveData) {

        BottomSheetBehavior

        if(liveData.value.size() > 0 && liveData.isValid == false) {
            mTvIsValid.setVisibility(View.VISIBLE);
        }
        else {
            mTvIsValid.setVisibility(View.GONE);
        }

        mAdapter.setData(liveData.value);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
