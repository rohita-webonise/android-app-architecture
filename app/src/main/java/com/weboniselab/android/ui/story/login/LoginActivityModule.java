package com.weboniselab.android.ui.story.login;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webonise on 16/2/18.
 */

@Module
public class LoginActivityModule {

    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager,
                                         SchedulerProvider schedulerProvider) {
        return new LoginViewModel(dataManager, schedulerProvider);
    }

}