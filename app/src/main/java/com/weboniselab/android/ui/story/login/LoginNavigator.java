package com.weboniselab.android.ui.story.login;

import com.weboniselab.android.data.remote.api.Place;

/**
 * Created by webonise on 13/2/18.
 */

public interface LoginNavigator {

    void login();

    void gotoHome();

    void showProgress(boolean isProgressing);

    void apiFailure(Throwable throwable);


}