package com.betclic.archimvp.core.android;

/**
 * Created by Mathieu Bertin on 06/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public interface BetclicContract<S extends BetclicViewModel> {

    interface Controller<S> {
        void attachView(S viewModel);
        void detachView();
    }

    interface ViewModel<S> {
    }
}
