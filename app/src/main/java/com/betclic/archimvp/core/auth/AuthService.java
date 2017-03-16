package com.betclic.archimvp.core.auth;

import io.reactivex.Single;

/**
 * Created by Mathieu Bertin on 08/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public interface AuthService {

    Single<AuthToken> getVisitorToken();
}
