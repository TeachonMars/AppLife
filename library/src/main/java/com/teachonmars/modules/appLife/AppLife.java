package com.teachonmars.modules.appLife;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.teachonmars.modules.appLife.internal.AppSpy;
import com.teachonmars.modules.appLife.listeners.ActivitySpyBase;
import com.teachonmars.modules.appLife.listeners.ActivitySpyBuild;
import com.teachonmars.modules.appLife.listeners.ActivitySpyLive;
import com.teachonmars.modules.appLife.listeners.ActivitySpyVisible;
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
            appContext = context.getApplicationContext();
            ((Application) appContext).registerActivityLifecycleCallbacks(appSpy);
        }
    }

    /**
     * Register a listener {@link ActivitySpyBase} to be called when activities evolves in their lifecycle
     *
     * @param listener {@link ActivitySpyBase} the listener to call. Some predefined abstract classes exist to regroup opposed lifecycle events :
     *                 {@link ActivitySpyBuild}, {@link ActivitySpyLive}, {@link ActivitySpyVisible}
     * @param hardRef  choose if listener should be a store in a {@link java.lang.ref.WeakReference} or directly as a hard reference
     */
    static public void register(ActivitySpyBase listener, boolean hardRef) {
        appSpy.registerListener(listener, hardRef);
    }

    /**
     * Unregister a listener {@link ActivitySpyBase} for stopping subsequent call to it.
     * Unregister is important for hard referenced listener added with {@linkplain  AppLife#register}
     *
     * @param listener activity spy to remove from AppLife
     */
    static public void unregister(ActivitySpyBase listener) {
        appSpy.unregisterListener(listener);
    }

    /**
     * Try to get current top activity, if one exist and still alive
     *
     * @return current top activity or null if none exist
     */
    @Nullable
    static public Activity getCurrentActivity() {
        return appSpy.tryGetCurrentActivity();
    }

    /**
     * Retrieve application context from everywhere
     *
     * @return application context never null
     */
    @NonNull
    static public Context getAppContext() {
        return appContext;
    }

    /**
     * Tool to verify if an Activity still exist
     *
     * @param activityHash hash of activity to verify, retrieved by {@link Object#hashCode()}
     * @return true if Activty exist, false otherwise
     */
    static public boolean isStillAlive(int activityHash) {
        return appSpy.isStillAlive(activityHash);
    }

    /**
     * Tool to verify if an Activity still exist
     *
     * @param activity activity to verify
     * @return true if Activty exist, false otherwise
     */
    static public boolean isStillAlive(Activity activity) {
        return isStillAlive(activity.hashCode());
    }
}
