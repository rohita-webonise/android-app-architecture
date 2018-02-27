package com.weboniselab.android.ui.story.home;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.app.AppUtils;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by webonise on 16/2/18.
 */

public class HomeViewModel extends BaseViewModel<HomeNavigator> {
    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getPlaceList(String query) {
        getNavigator().showProgress(true);
        getCompositeDisposable().add(getDataManager().getApiService()
                .getPlaceList(query)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Place>() {
                    @Override
                    public void accept(Place response) throws Exception {

                        getNavigator().showProgress(false);
                        getNavigator().updatePlaceList(response.getResults().get(0).getFormattedAddress());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().showProgress(false);
                        getNavigator().apiFailure(throwable);
                    }
                }));


    }

    public void addUser(String query){
        getCompositeDisposable().add(getDataManager()
                .getUserById(query)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> userList) throws Exception {
                        if (userList != null) {
                            getNavigator().showProgress(false);
                            if(AppUtils.isListNotNullEmpty(userList)) {
                                getNavigator().updatePlaceList(userList.get(0).toString());
                            }

                        }
                    }
                }));
    }
}
