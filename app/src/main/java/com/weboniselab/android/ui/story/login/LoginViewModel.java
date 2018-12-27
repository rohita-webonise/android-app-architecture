package com.weboniselab.android.ui.story.login;

import android.util.Log;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.remote.pojo.User;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.app.AppLogger;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by webonise on 1/2/18.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void doLogin(User user) {
        getNavigator().showProgress(true);
        getCompositeDisposable().add(getDataManager().getApiService()
                .doLogin(user).retry(3)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<User>() {
                    @Override
                    public void onNext(User user) {
                        getNavigator().showProgress(false);
                        getNavigator().gotoHome();
                        Log.i("doLogin","onNext!!"+user.getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getNavigator().showProgress(false);
                        getNavigator().apiFailure(e);
                        Log.e("doLogin","onError!!"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getNavigator().showProgress(false);
                        Log.i("doLogin","onComplete!!");
                    }

                }));

    }

  /*  new Consumer<User>() {
        @Override
        public void accept(User response) throws Exception {

            getNavigator().showProgress(false);
            getNavigator().gotoHome();
            Log.i("doLogin","Success!!"+response.getToken());
        }
    }, new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            getNavigator().showProgress(false);
            getNavigator().apiFailure(throwable);
            Log.e("doLogin",""+throwable.getMessage());
        }
    })*/

}
