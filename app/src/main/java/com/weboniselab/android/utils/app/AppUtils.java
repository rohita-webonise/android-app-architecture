package com.weboniselab.android.utils.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.weboniselab.android.R;

import java.util.ArrayList;
import java.util.List;


public final class AppUtils {

    private AppUtils() {
        // This class is not publicly instantiable
    }

    /**
     * check if index is within the bounds of {@link ArrayList} if yes then true else false.
     * @param arrayList
     * @return
     */
    public static boolean isIndexWithInBounds(ArrayList arrayList,int index) {

        return (index >= 0) && (index < arrayList.size());
    }

    /**
     * check if {@link ArrayList} is not null and empty then return true else false.
     * @param list
     * @return
     */
    public static boolean isListNotNullEmpty(ArrayList list){

        if(list!=null && !list.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * check if {@link java.util.List} is not null and empty then return true else false.
     * @param list
     * @return
     */
    public static boolean isListNotNullEmpty(List list){

        if(list!=null && !list.isEmpty()){
            return true;
        }
        return false;
    }

    public static void openPlayStoreForApp(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_market_link) + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(context
                            .getResources()
                            .getString(R.string.app_google_play_store_link) + appPackageName)));
        }
    }
}
