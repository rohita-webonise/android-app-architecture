
package com.weboniselab.android.ui.story.home.place;

import com.weboniselab.android.data.remote.api.Place;


public interface PlaceNavigator {

    void updatePlace(Place place);

    void handleError(Throwable throwable);

}
