package com.betclic.archimvp.core.android;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by Mathieu Bertin on 06/03/2017.
 * Copyright © 2017 Betclic. All rights reserved.
 */

public abstract class BetclicActivity<T extends BetclicController> extends RxAppCompatActivity {

    /* @Inject*/ T mController;
    /* @Inject */ BetclicViewModel mViewModel;

    public abstract void injectDependencies();

    /**
     * Lifecycle Method
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs)
    {
        if (mController != null)
        {
            mController.attachView(mViewModel);
        }

        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy()
    {
        if (mController!= null)
        {
            mController.detachView();
        }

        super.onDestroy();
    }


}
