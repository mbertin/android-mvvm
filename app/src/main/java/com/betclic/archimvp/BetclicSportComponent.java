package com.betclic.archimvp;

import com.betclic.archimvp.core.network.NetworkModule;
import com.betclic.archimvp.core.storage.StorageModule;
import com.betclic.archimvp.deezer.DeezerActivity;
import com.betclic.archimvp.deezer.DeezerManager;
import com.betclic.archimvp.deezer.DeezerViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mathieu Bertin on 03/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
@Singleton
@Component(modules = {NetworkModule.class})
public interface BetclicSportComponent {

    DeezerViewModel getDeezerViewModel();

    void inject(DeezerActivity activity);

}
