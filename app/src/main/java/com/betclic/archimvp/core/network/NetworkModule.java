package com.betclic.archimvp.core.network;

import com.betclic.archimvp.BuildConfig;
import com.betclic.archimvp.deezer.RealmListAdapter;
import com.betclic.archimvp.deezer.Track;
import com.betclic.archimvp.deezer.TrackAdapter;
import com.facebook.stetho.inspector.network.RequestBodyHelper;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Mathieu Bertin on 07/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Module
public class NetworkModule {

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.addNetworkInterceptor(new StethoInterceptor());
        okClient.addNetworkInterceptor(new ETagInterceptor());
        return okClient.build();
    }

    @Provides
    @Named("globalRetrofit")
    Retrofit provideGlobalRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BuildConfig.GLOBAL_BASE_URL)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

        return retrofit;
    }

    @Provides
    @Named("universeRetrofit")
    Retrofit provideUniverseRetrofit(OkHttpClient okHttpClient) {

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(BuildConfig.UNIVERSE_BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(MoshiConverterFactory.create())
                        .build();

        return retrofit;
    }

    @Provides
    @Named("deezerRetrofit")
    Retrofit provideDeezerRetrofit(OkHttpClient okHttpClient) {

        Moshi moshi = new Moshi.Builder()
                .add(new RealmListAdapter.Factory())
                .build();


        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.deezer.com/")
                        .client(okHttpClient)
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

        return retrofit;
    }

    public class ETagInterceptor implements Interceptor {

        private static final String ETAG_KEY = "ETag";
        private HashMap<String, String> etags = new HashMap<>();

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            // GET url et e-tag
            String url = request.url().toString();
            Map<String, List<String>> map = request.headers().toMultimap();
            if (etags.containsKey(url)) {
                String etag = etags.get(url);
                map.put(ETAG_KEY, Arrays.asList(etag));
            }

            Response response;

            try {
                response = chain.proceed(request);
                String receivedEtag =response.headers().get(ETAG_KEY);
                etags.put(url, receivedEtag);
            } catch (IOException e) {
                throw e;
            }

            return response;
        }
    }

    public class JWTInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            return null;
        }
    }
}
