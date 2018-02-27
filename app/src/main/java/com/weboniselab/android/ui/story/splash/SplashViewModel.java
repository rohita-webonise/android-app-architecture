package com.weboniselab.android.ui.story.splash;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by webonise on 1/2/18.
 */

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager,
                           SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }



}