package com.weboniselab.android.ui.story.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.weboniselab.android.R;
import com.weboniselab.android.ui.main.BaseActivity;
import com.weboniselab.android.ui.story.login.LoginActivity;
import com.weboniselab.android.utils.app.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webonise on 1/2/18.
 */

public class SplashActivity extends BaseActivity<SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initUiViews();
        mSplashViewModel.setNavigator(this);
        initUiViews();
    }


    public void initUiViews() {
        showProgress(true);
        getUiHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showProgress(false);
                openLoginActivity();
            }
        },3000);
    }

    @Override
    public void showProgress(boolean isProgressing) {
        progressBar.setVisibility(isProgressing ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void openLoginActivity() {
        ActivityUtils.goToNextActivity(this,LoginActivity.class,null,true);
    }


    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }


}
