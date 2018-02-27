package com.weboniselab.android.data;

import com.weboniselab.android.data.local.db.DbHelper;
import com.weboniselab.android.data.local.prefs.PreferencesHelper;
import com.weboniselab.android.data.remote.ApiHelper;
import com.weboniselab.android.data.remote.ApiService;
import com.weboniselab.android.data.remote.api.UserApi;

import io.reactivex.Observable;



public interface DataManager extends DbHelper, PreferencesHelper {

    ApiService getApiService();

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
