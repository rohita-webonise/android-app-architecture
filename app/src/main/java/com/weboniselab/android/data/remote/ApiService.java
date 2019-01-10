package com.weboniselab.android.data.remote;

import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.PlacePhotoResult;
import com.weboniselab.android.data.remote.api.PlaceResults;
import com.weboniselab.android.data.remote.pojo.UserApi;
import com.weboniselab.android.data.remote.pojo.UserData;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by webonise on 16/2/18.
 */

public class ApiService{

    private final Retrofit mRetrofit;
    private final ApiHelper mApiHelper;

    @Inject
    public ApiService(Retrofit retrofit,ApiHelper apiHelper){
        mRetrofit = retrofit;
        mApiHelper = apiHelper;
    }


    public Observable<Place> getPlaceList(String query) {
        return mApiHelper.getPlaceList(query);
    }


    public Observable<PlacePhotoResult> getPlacePhotos(String placeId) {
        return mApiHelper.getPlacePhotos(placeId);
    }


    public Observable<PlaceResults> getCurrentPlace(String address) {
        return mApiHelper.getCurrentPlace(address);
    }

    public String getPlace(){
        return "indore";
    }




    public Observable<Response<UserApi>> doLogin(UserApi userApi) {
        return mApiHelper.login(userApi);
    }

    public Observable<Response<UserData>> getUserById(String id) {
        return mApiHelper.getUserById(id);
    }
}


