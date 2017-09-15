package com.teachonmars.module.appLife;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.teachonmars.module.appLife.internal.AppSpy;
import com.teachonmars.module.autoContext.annotation.NeedContext;

public class AppLife {
    private static AppSpy appSpy = new AppSpy();
    private static Context appContext;

    @NeedContext
    static public void init(Context context) {
        if (context != null && context instanceof Application) {
            appContext = context.getApplicationContext();
            ((Application) context).registerActivityLifecycleCallbacks(appSpy);
        }
    }

    public static void register(ActivityBaseSpy listener, boolean hardRef) {
        appSpy.registerListener(listener, hardRef);
    }

    public static void unregister(ActivityBaseSpy listener) {
        appSpy.unregisterListener(listener);
    }

//    public static void register(Application.ActivityLifecycleCallbacks listener) {
//        appSpy.registerListener(listener);
//    }
//
//    public static void unregister(Application.ActivityLifecycleCallbacks listener) {
//        appSpy.unregisterListener(listener);
//    }

    public static Activity getCurrentActivity() {
        return appSpy.tryGetCurrentActivity();
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static boolean isStillAlive(int activityHash) {
        return appSpy.isStillAlive(activityHash);
    }

    public static boolean isStillAlive(Activity activity) {
        return isStillAlive(activity.hashCode());
    }
}
