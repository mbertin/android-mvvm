package com.betclic.archimvp.deezer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.betclic.archimvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by Mathieu Bertin on 15/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

class DeezerPlaylistAdapter extends RecyclerView.Adapter<DeezerPlaylistAdapter.PlaylistTitleViewHolder> {

    private List<String> mTitles;

    @Override
    public PlaylistTitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_crime, parent, false);
        return new PlaylistTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlaylistTitleViewHolder holder, int position) {
        holder.setTitle(mTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public void setData(List<String> value) {
        mTitles = value;
        notifyDataSetChanged();
    }

    public class PlaylistTitleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        public PlaylistTitleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTitle(String s) {
            title.setText(s);
        }
    }
}
