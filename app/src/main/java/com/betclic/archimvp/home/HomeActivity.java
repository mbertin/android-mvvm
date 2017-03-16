package com.betclic.archimvp.home;

import android.os.Bundle;
import android.widget.TextView;

import com.betclic.archimvp.BetclicSportApp;
import com.betclic.archimvp.BuildConfig;
import com.betclic.archimvp.R;
import com.betclic.archimvp.user.UserManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends RxAppCompatActivity {

    @Inject UserManager mUserManager;

    @BindView(R.id.text)
    protected TextView mTvTitle;

    public void injectDependencies() {
        ((BetclicSportApp) getApplication()).getUserComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies();

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTvTitle.setText(mUserManager.getUserName());
    }
}
