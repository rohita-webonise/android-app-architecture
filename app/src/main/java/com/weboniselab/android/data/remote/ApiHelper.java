package com.weboniselab.android.data.remote;

import com.weboniselab.android.BuildConfig;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.PlacePhotoResult;
import com.weboniselab.android.data.remote.api.PlaceResults;
import com.weboniselab.android.data.remote.pojo.UserApi;
import com.weboniselab.android.data.remote.pojo.UserData;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiHelper {

    @GET("place/textsearch/json?&key="+ BuildConfig.API_KEY)
    Observable<Place> getPlaceList(@Query("query") String query);

    @GET("place/details/json?&key="+ BuildConfig.API_KEY)
    Observable<PlacePhotoResult> getPlacePhotos(@Query("placeid") String placeId);

    @GET("geocode/json?&key="+ BuildConfig.API_KEY)
    Observable<PlaceResults> getCurrentPlace(@Query("address") String address);


    /**************************************************************************
     * Test API
     */

    @POST("login")
    Observable<Response<UserApi>> login(@Body UserApi userApi);

    @GET("users/"+ "{id}")
    Observable<Response<UserData>> getUserById(@Path("id") String id);

}


