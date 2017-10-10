package com.teachonmars.modules.appLife.listeners;

import android.app.Activity;

/**
 * Base interface to implement for follow all activity lifecycle : building &amp; destroying, live &amp; not, visible &amp; not.
 * Derived abstract classes can be used to follow only one of the three part of activity's lifecycle (see {@link ActivitySpyBuild}, {@link ActivitySpyLive} and {@link ActivitySpyVisible})
 */
public interface ActivitySpyBase {
    void onCreate(Activity activity);

    void onDestroy(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);
}
