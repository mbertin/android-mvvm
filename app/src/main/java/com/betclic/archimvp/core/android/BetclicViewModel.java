package com.betclic.archimvp.core.android;

import android.databinding.BaseObservable;

/**
 * Created by Mathieu Bertin on 06/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public abstract class BetclicViewModel extends BaseObservable {

    public abstract void setController(BetclicController controller);

}
