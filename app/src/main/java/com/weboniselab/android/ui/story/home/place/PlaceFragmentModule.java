

package com.weboniselab.android.ui.story.home.place;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.remote.api.Result;
import com.weboniselab.android.ui.main.ViewModelProviderFactory;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class PlaceFragmentModule {

    @Provides
    PlaceViewModel blogViewModel(DataManager dataManager,
                                 SchedulerProvider schedulerProvider) {
        return new PlaceViewModel(dataManager, schedulerProvider);
    }

    @Provides
    PlaceAdapter provideBlogAdapter() {
        return new PlaceAdapter(new ArrayList<Result>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(PlaceFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    ViewModelProvider.Factory provideBlogViewModel(PlaceViewModel placeViewModel) {
        return new ViewModelProviderFactory<>(placeViewModel);
    }

}
