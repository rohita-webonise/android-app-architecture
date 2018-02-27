package com.weboniselab.android.ui.story.home;

import com.weboniselab.android.data.remote.api.Place;

/**
 * Created by webonise on 16/2/18.
 */

public interface HomeNavigator {

    void showProgress(boolean isProgressing);

    void updatePlaceList(String response);

    void showUser(String userDetails);

    void apiFailure(Throwable throwable);
}
