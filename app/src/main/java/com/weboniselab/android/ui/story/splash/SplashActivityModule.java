

package com.weboniselab.android.ui.story.splash;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {
    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager,
                                           SchedulerProvider schedulerProvider) {
        return new SplashViewModel(dataManager, schedulerProvider);
    }
}
