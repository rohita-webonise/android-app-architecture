package com.weboniselab.android.data;

import android.content.Context;

import com.weboniselab.android.data.local.db.DbHelper;
import com.weboniselab.android.data.local.db.table.User;
import com.weboniselab.android.data.local.prefs.PreferencesHelper;
import com.weboniselab.android.data.remote.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiService mApiService;

    @Inject
    public AppDataManager(Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiService apiService) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiService = apiService;
    }

    @Override
    public ApiService getApiService() {
        return mApiService;
    }



    @Override
    public Observable<Boolean> insertUser(User user) {
        return mDbHelper.insertUser(user);
    }

    @Override
    public Observable<List<User>> getAllUsers() {
        return mDbHelper.getAllUsers();
    }

    @Override
    public Observable<List<User>> getUserById(String userId) {
        return mDbHelper.getUserById(userId);
    }




}
