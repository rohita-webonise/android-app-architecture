package com.weboniselab.android.ui.story.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.weboniselab.android.R;
import com.weboniselab.android.data.remote.pojo.User;
import com.weboniselab.android.ui.main.BaseActivity;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.ui.story.home.HomeActivity;
import com.weboniselab.android.utils.app.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webonise on 1/2/18.
 */

public class LoginActivity extends BaseActivity<LoginViewModel> implements LoginNavigator, View.OnClickListener {


    @Inject
    LoginViewModel mLoginViewModel;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.edtUserName)
    EditText edtUserName;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;


    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    public static <V extends BaseViewModel> Intent getStartIntent(BaseActivity<V> vBaseActivity) {

        return vBaseActivity.getIntent();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initUiViews();
        mLoginViewModel.setNavigator(this);
        initUiViews();
    }

    public void initUiViews() {
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void gotoHome() {
        ActivityUtils.goToNextActivity(this, HomeActivity.class, null, true);
    }

    @Override
    public void showProgress(boolean isProgressing) {
        progressBar.setVisibility(isProgressing ? View.VISIBLE : View.GONE);
    }

    @Override
    public void apiFailure(Throwable throwable) {

    }

    @Override
    public void login() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn: {
                User user = new User();
                user.setEmail(edtUserName.getText().toString());
                user.setPassword(edtPassword.getText().toString());

                mLoginViewModel.doLogin(user);
                break;
            }
        }
    }
}
