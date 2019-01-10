package com.weboniselab.android.ui.main;


import android.arch.lifecycle.ViewModel;

import com.weboniselab.android.data.DataManager;
import com.weboniselab.android.data.remote.ApiStatus;
import com.weboniselab.android.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;

/**
 * Created by webonise on 1/2/18.
 */


public abstract class BaseViewModel<N> extends ViewModel {

    private N mNavigator;
    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;

    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel(DataManager dataManager,
                         SchedulerProvider schedulerProvider) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public ApiStatus validateResponse(Response response) {
        ApiStatus apiStatus = new ApiStatus();
        if(response!=null)
        {

            int code = response.code();
            apiStatus.setCode(code);
            apiStatus.setBodyAvailable(response.body()!= null ? true : false);
                if (code >= 200 && code < 300) {
                    apiStatus.setStatusCode(ApiStatus.ApiCallStatus.SUCCESS);
                    apiStatus.setSuccess(true);
                }else if (code >= 400 && code < 500) {
                    apiStatus.setStatusCode(ApiStatus.ApiCallStatus.FAILURE);
                    apiStatus.setSuccess(false);
                } else if (code >= 500) {
                    apiStatus.setStatusCode(ApiStatus.ApiCallStatus.ERROR);
                    apiStatus.setSuccess(false);
                }
        }
        return apiStatus;
    }
}