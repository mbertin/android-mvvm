package com.betclic.archimvp.user;

import com.betclic.archimvp.home.HomeActivity;
import com.betclic.archimvp.core.auth.AuthModule;
import com.betclic.archimvp.core.network.NetworkModule;
import com.betclic.archimvp.core.storage.StorageModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */
/*@Singleton
@Component(modules = {NetworkModule.class, StorageModule.class, UserModule.class, AuthModule.class})
*/
public interface UserComponent {
    void inject(HomeActivity activity);
    UserManager provideUserManager();
}
