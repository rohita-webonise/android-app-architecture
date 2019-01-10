package com.weboniselab.android.ui.story.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.weboniselab.android.R;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.ui.main.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by webonise on 16/2/18.
 */

public class HomeActivity extends BaseActivity<HomeViewModel> implements HomeNavigator {

    @Inject
    HomeViewModel mHomeViewModel;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.homeTxt)
    TextView homeTxt;

    @Override
    public HomeViewModel getViewModel() {
        return mHomeViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initUiViews();
        mHomeViewModel.setNavigator(this);
        getViewModel().getPlaceList("Indore");
        initUiViews();


    }


    public void initUiViews() {
    }


    @Override
    public void showProgress(boolean isProgressing) {
        progressBar.setVisibility(isProgressing ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updatePlaceList(String response) {
        homeTxt.setText(response);
    }

    @Override
    public void showUserList(List<User> users) {
        homeTxt.setText("");
        for (User user : users){
            homeTxt.append(users.toString()+" \n");
        }

    }

    @Override
    public void showUser(String userDetails) {

    }

    @Override
    public void apiFailure(Throwable throwable) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewModel().getUserById("2");
    }
}

