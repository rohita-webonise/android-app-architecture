package com.weboniselab.android.ui.story.login;

import android.util.Log;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.remote.ApiStatus;
import com.weboniselab.android.data.remote.pojo.UserApi;
import com.weboniselab.android.ui.main.BaseViewModel;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

/**
 * Created by webonise on 1/2/18.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void doLogin(UserApi userApi) {
        getNavigator().showProgress(true);
        getCompositeDisposable().add(getDataManager().getApiService()
                .doLogin(userApi)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Response<UserApi>>() {
                    @Override
                    public void accept(Response<UserApi> response) throws Exception {

                        ApiStatus apiStatus = validateResponse(response);
                        getNavigator().showProgress(false);

                        if(apiStatus.isSuccess()){

                            getNavigator().apiSuccess("");
                            getNavigator().gotoHome();
                        }else{
                            getNavigator().apiFailure("");
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

  /*  new Consumer<UserApi>() {
        @Override
        public void accept(UserApi response) throws Exception {

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

  /*.subscribeWith(new DisposableObserver<Response<UserApi>>() {
        @Override
        public void onNext(Response<UserApi> user) {
            getNavigator().showProgress(false);
            Log.i("doLogin","onNext!!"+user.code());
        }

        @Override
        public void onError(Throwable e) {
            getNavigator().showProgress(false);
            getNavigator().apiFailure(e);
            HttpException error = (HttpException)e;
            String errorBody = error.response().errorBody().toString();
            Log.e("doLogin","onError code!!"+error.response().code());
            Log.e("doLogin","onError error body!!"+errorBody);

        }

        @Override
        public void onComplete() {
            getNavigator().showProgress(false);
            Log.i("doLogin","onComplete!!");
            getNavigator().gotoHome();
        }

    }));*/

}
