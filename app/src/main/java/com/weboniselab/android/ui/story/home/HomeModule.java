package com.weboniselab.android.ui.story.home;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by webonise on 16/2/18.
 */
@Module
public class HomeModule {

    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager,
                                       SchedulerProvider schedulerProvider){
        return new HomeViewModel(dataManager,schedulerProvider);
    }
}
