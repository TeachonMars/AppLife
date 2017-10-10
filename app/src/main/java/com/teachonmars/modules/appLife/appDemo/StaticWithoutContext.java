package com.teachonmars.modules.appLife.appDemo;

import android.app.Activity;
import android.util.Log;

import com.teachonmars.modules.appLife.AppLife;
import com.teachonmars.modules.appLife.listeners.ActivitySpyBase;

public class StaticWithoutContext {


    private static final String TAG = StaticWithoutContext.class.getSimpleName();

    public static void init() {
        AppLife.register(new ActivitySpyBase() {
            @Override
            public void onCreate(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onCreate"));
            }

            @Override
            public void onStart(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onStart"));
            }

            @Override
            public void onResume(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onResume"));
            }

            @Override
            public void onPause(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onPause"));
            }

            @Override
            public void onStop(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onStop"));
            }

            @Override
            public void onDestroy(Activity activity) {
                Log.d(TAG, activity.getString(R.string.activityStatus, "onDestroy"));
            }
        }, true);
    }

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
