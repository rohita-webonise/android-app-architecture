package com.weboniselab.android.ui.story.login;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.remote.api.LoginRequest;
import com.weboniselab.android.data.remote.api.LoginResponse;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.UserApi;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by webonise on 1/2/18.
 */

class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void doLogin(String email,String password){
        getNavigator().showProgress(true);
        User user = new User();
        user.setUserId("1");
        user.setEmail(email);
        user.setName(password);
        Observable<Boolean> add = getDataManager().insertUser(user);
        getNavigator().showProgress(false);
        getNavigator().gotoHome();
        /*getCompositeDisposable().add(getDataManager().updateUserInfo(userApi)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Place>() {
                    @Override
                    public void accept(Place response) throws Exception {
                        getNavigator().showProgress(false);
                        getNavigator().gotoHome();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getNavigator().showProgress(false);
                        getNavigator().apiFailure(throwable);
                    }
                }));*/
    }

}
