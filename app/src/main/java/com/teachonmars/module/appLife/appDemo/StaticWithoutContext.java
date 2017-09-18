package com.teachonmars.module.appLife.appDemo;

import android.app.Activity;
import android.util.Log;

import com.teachonmars.module.appLife.AppLife;

public class StaticWithoutContext {

    private static final String TAG = StaticWithoutContext.class.getSimpleName();

    public static void test() {
        Activity currentActivity = AppLife.getCurrentActivity();
        String msg;
        if (currentActivity != null) {
            msg = currentActivity.getString(R.string.activityIsReady, currentActivity.getClass().getSimpleName());
        } else {
            msg = AppLife.getAppContext().getString(R.string.activityIsNotReady);
        }
        Log.d(TAG, msg);
    }
}
