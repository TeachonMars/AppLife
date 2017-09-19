package com.teachonmars.modules.appLife;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.teachonmars.modules.appLife.internal.AppSpy;
import com.teachonmars.modules.appLife.listeners.ActivityActiveSpy;
import com.teachonmars.modules.appLife.listeners.ActivityBaseSpy;
import com.teachonmars.modules.appLife.listeners.ActivityCreationSpy;
import com.teachonmars.modules.appLife.listeners.ActivityVisibleSpy;
import com.teachonmars.modules.autoContext.annotation.NeedContext;

public class AppLife {
    private static AppSpy appSpy = new AppSpy();
    private static Context appContext;


    /**
     * Init library.
     * Don't call this method, it will be called by AutoContext library
     *
     * @param context appContext
     */

    @NeedContext(priority = 1)
    static public void init(Context context) {
        if (context != null) {
            if (context instanceof Application) {
                appContext = context;
            } else {
                appContext = context.getApplicationContext();
            }
            ((Application) appContext).registerActivityLifecycleCallbacks(appSpy);
        }
    }

    /**
     * Register a listener {@link ActivityBaseSpy} to be called when activities evolves in their lifecycle
     *
     * @param listener {@link ActivityBaseSpy} the listener to call. Some predefined abstract classes exist to regroup opposed lifecycle events :
     *                 {@link ActivityCreationSpy}, {@link ActivityVisibleSpy}, {@link ActivityActiveSpy}
     * @param hardRef  choose if listener should be a store in a {@link java.lang.ref.WeakReference} or directly as a hard reference
     */
    public static void register(ActivityBaseSpy listener, boolean hardRef) {
        appSpy.registerListener(listener, hardRef);
    }

    /**
     * Unregister a listener {@link ActivityBaseSpy} for stopping subsequent call to it.
     * Unregister is important for hard referenced listener added with {@linkplain  AppLife#register}
     *
     * @param listener
     */
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

    /**
     * Try to get current top activity, if one exist and still alive
     *
     * @return current top activity or null if none exist
     */
    public static
    @Nullable
    Activity getCurrentActivity() {
        return appSpy.tryGetCurrentActivity();
    }

    /**
     * Retrieve application context from everywhere
     *
     * @return application context never null
     */
    public static
    @NonNull
    Context getAppContext() {
        return appContext;
    }

    /**
     * Tool to verify if an Activity still exist
     *
     * @param activityHash hash of activity to verify, retrieved by {@link Object#hashCode()}
     * @return true if Activty exist, false otherwise
     */

    public static boolean isStillAlive(int activityHash) {
        return appSpy.isStillAlive(activityHash);
    }

    /**
     * Tool to verify if an Activity still exist
     *
     * @param activity activity to verify
     * @return true if Activty exist, false otherwise
     */

    public static boolean isStillAlive(Activity activity) {
        return isStillAlive(activity.hashCode());
    }
}
