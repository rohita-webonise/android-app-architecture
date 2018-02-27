package com.weboniselab.android.data.remote;

import com.weboniselab.android.BuildConfig;
import com.weboniselab.android.data.remote.api.Place;
import com.weboniselab.android.data.remote.api.PlacePhotoResult;
import com.weboniselab.android.data.remote.api.PlaceResults;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {

    @GET("place/textsearch/json?&key="+ BuildConfig.API_KEY)
    Observable<Place> getPlaceList(@Query("query") String query);

    @GET("place/details/json?&key="+ BuildConfig.API_KEY)
    Observable<PlacePhotoResult> getPlacePhotos(@Query("placeid") String placeId);

    @GET("geocode/json?&key="+ BuildConfig.API_KEY)
    Observable<PlaceResults> getCurrentPlace(@Query("address") String address);
}
