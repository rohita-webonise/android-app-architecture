
package com.weboniselab.android.ui.story.home.place;

import android.arch.lifecycle.MutableLiveData;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.Result;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;



public class PlaceViewModel extends BaseViewModel<PlaceNavigator> {

    private List<Result> mResultList ;
    private final MutableLiveData<List<Result>> placeLiveData;

    public PlaceViewModel(DataManager dataManager,
                          SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        placeLiveData = new MutableLiveData<>();
        fetchBlogs();
    }

    public void fetchBlogs() {
        //setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getApiService().getPlaceList("Indore")
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Place>() {
                    @Override
                    public void accept(@NonNull Place placeResponse)
                            throws Exception {
                        if (placeResponse != null && placeResponse.getResults() != null) {
                            placeLiveData.setValue(placeResponse.getResults());
                        }
                        //setIsLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        //setIsLoading(false);
                        getNavigator().handleError(throwable);
                    }
                }));
    }

    public MutableLiveData<List<Result>> getPlaceLiveData() {
        return placeLiveData;
    }



    public void addBlogItemsToList(List<Result> blogs) {
        mResultList.clear();
        mResultList.addAll(blogs);
    }

    public List<Result> getResultList() {
        return mResultList;
    }
}
