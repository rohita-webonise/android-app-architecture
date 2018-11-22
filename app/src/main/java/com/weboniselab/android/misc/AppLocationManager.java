package com.weboniselab.android.misc;

import android.content.Context;
import android.location.Location;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.weboniselab.android.data.local.prefs.AppPreferencesHelper;
import com.google.android.gms.location.LocationCallback;
import com.weboniselab.android.utils.app.AppLogger;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.annotations.NonNull;

/**
 * Created by rohit.anvekar on 25/4/18.
 */
@Singleton
public class AppLocationManager {

    private final String TAG = AppLocationManager.class.getName();
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private Context mContext;
    private AppPreferencesHelper mAppPreferencesHelper;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Location mLocation;

    @Inject
    public AppLocationManager(Context context, AppPreferencesHelper appPreferencesHelper) {
        mContext = context;
        mAppPreferencesHelper = appPreferencesHelper;
        startLocationUpdates();
    }

    public void startLocationUpdates() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                onNewLocation(locationResult.getLastLocation());
            }
        };

        createLocationRequest();

        //# Location access way by:
        //Get One time location call back when get called explicitly
        //getLastLocation();

        //# Call once  for periodically updates of location
        //requestLocationUpdates();
    }

    private void createLocationRequest() {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    /**
     * Makes a request for location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void requestLocationUpdates() {
        AppLogger.i(TAG, "Requesting location updates");
        try {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, Looper.myLooper());
        } catch (SecurityException unlikely) {
            AppLogger.e(TAG, "Lost location permission. Could not request updates. " + unlikely);
        }
    }

    /**
     * Removes location updates. Note that in this sample we merely log the
     * {@link SecurityException}.
     */
    public void removeLocationUpdates() {
        AppLogger.i(TAG, "Removing location updates");
        try {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            mLocationCallback = null;
        } catch (SecurityException unlikely) {
            AppLogger.e(TAG, "Lost location permission. Could not remove updates. " + unlikely);
        }
    }


    /**
     * get Last Location
     */
    public void getLastLocation() {
        try {
            mFusedLocationClient.getLastLocation()
                    .addOnCompleteListener(new OnCompleteListener<Location>() {
                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            if (task.isSuccessful() && task.getResult() != null) {
                                mLocation = task.getResult();
                                AppLogger.i(TAG, "getLastLocation Location: Lat: " + mLocation.getLatitude() + " Long: " + mLocation.getLongitude());
                            } else {
                                AppLogger.i(TAG, "Failed to get location.");
                            }
                        }
                    });
        } catch (SecurityException unlikely) {
            AppLogger.e(TAG, "Lost location permission." + unlikely);
        }
    }

    private void onNewLocation(Location location) {
        mLocation = location;
        AppLogger.i(TAG, "onNewLocation Location: Lat: " + mLocation.getLatitude() + " Long: " + mLocation.getLongitude());
    }


    public Location getLocation(){
        return mLocation;
    }
}
