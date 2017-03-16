package com.betclic.archimvp.deezer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Mathieu Bertin on 14/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public interface DeezerService {

    @Headers("isPublic: true")
    @GET("user/2529/playlists")
    Observable<PlaylistsResponse> getPlaylists();

    @GET
    Observable<TracksResponse> getPlaylistTracks(@Url String url);
}
