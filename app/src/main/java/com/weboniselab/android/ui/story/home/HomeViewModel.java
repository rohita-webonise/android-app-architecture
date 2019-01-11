package com.weboniselab.android.ui.story.home;

import android.util.Log;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.remote.ApiStatus;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.pojo.UserApi;
import com.weboniselab.android.data.remote.pojo.UserData;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.app.AppUtils;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

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
                .getPlaceList(query).retry(5)
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


    public void getUserById(String id) {
        getNavigator().showProgress(true);
        getCompositeDisposable().add(getDataManager().getApiService()
                .getUserById(id)
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(new Consumer<Response<UserData>>() {
                    @Override
                    public void accept(Response<UserData> response) throws Exception {

                        ApiStatus apiStatus = validateResponse(response);
                        if(apiStatus.isSuccess()){
                           UserData userData = new UserData();
                           userData  = response.body();
                            User user = new User();
                            user.setUserId(userData.getData().getId().toString());
                            user.setFirstName(userData.getData().getFirstName());
                            user.setLastName(userData.getData().getLastName());
                            user.profileImageUrl = userData.getData().getAvatar();
                            getDataManager().insertUser(user)
                                    .subscribeOn(getSchedulerProvider().io())
                                    .subscribe(new Consumer<Boolean>() {
                                @Override
                                public void accept(Boolean aBoolean) throws Exception {
                                    getDataManager().getAllUsers().observeOn(getSchedulerProvider().ui())
                                            .subscribeOn(getSchedulerProvider().io())
                                            .subscribe(new Consumer<List<User>>() {
                                        @Override
                                        public void accept(List<User> users) throws Exception {

                                            getNavigator().showProgress(false);

                                            getNavigator().showUserList(users);

                                        }
                                    });

                                }
                            });
                        }else{
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().showProgress(false);
                        getNavigator().apiFailure(throwable);
                        Log.e("doLogin",""+throwable.getMessage());
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

    public void addUser1(String query){
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
