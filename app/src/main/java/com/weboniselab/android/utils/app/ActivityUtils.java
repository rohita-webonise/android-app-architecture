package com.weboniselab.android.utils.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

public class ActivityUtils {
    /**
     * Method to start new activity without bundle and without finishing the current one
     *
     * @param context Current Activity Context
     * @param clazz   Activity to be started
     */
    public static void goToNextActivity(Context context, Class clazz) {
        goToNextActivity(context, clazz, null);
    }

    /**
     * Method to start new activity with passed bundle and without finishing the current activity
     *
     * @param context Current Activity Context
     * @param clazz   Activity to be started
     * @param bundle  Bundle data to be passed to new Activity
     */
    public static void goToNextActivity(Context context, Class clazz, Bundle bundle) {
        goToNextActivity(context, clazz, bundle, false);
    }

    /**
     * Method to start new activity with passed bundle and boolean variable to decide whether to
     * finish the activity or not
     *
     * @param context        Current Activity Context
     * @param clazz          Activity to be started
     * @param bundle         Bundle data to be passed to new Activity
     * @param finishActivity boolean variable to decide whether to finish the activity or not
     */
    public static void goToNextActivity(Context context, Class clazz, Bundle bundle, boolean
            finishActivity) {
        final Intent intent = new Intent(context, clazz);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (finishActivity && context instanceof Activity) {
            ((Activity) context).finish();
        }
    }


    /**
     * to check value of intent bundle.
     *
     * @param intent
     * @return
     */
    public static boolean isIntentBundleAvailable(Intent intent) {
        return (intent != null && intent.getExtras() != null) ? true : false;
    }

    /**
     * Method to  start activity by setting request code to return when
     * result code send back towards the starting point be in it's previous
     * screen without any flow break.
     *
     * @param activity
     * @param clazz
     * @param code
     */
    public static void startActivityForResult(Activity activity, Class clazz, Bundle bundle, int code) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, code);
    }

    /**
     * Method to start new activity with passed bundle and boolean variables to decide whether to
     * clear backstack and finish the activity or not
     *
     * @param context        Current Activity Context
     * @param clazz          Activity to be started
     * @param bundle         Bundle data to be passed to new Activity
     * @param finishActivity boolean variable to decide whether to finish the activity or not
     * @param clearBackstack boolean variable to decide whether to clear the backstack or not
     */
    public static void goToNextActivity(Context context, Class clazz, Bundle bundle, boolean
            finishActivity, boolean clearBackstack) {
        final Intent intent = new Intent(context, clazz);

        if (clearBackstack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (finishActivity && context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

}
