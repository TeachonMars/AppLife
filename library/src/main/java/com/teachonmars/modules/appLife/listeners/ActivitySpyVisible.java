package com.teachonmars.modules.appLife.listeners;

import android.app.Activity;

/**
 * Derived abstract class used to follow only activity's lifecycle is visible (onResume &amp; onPause)
 */
abstract public class ActivitySpyVisible implements ActivitySpyBase {
    @Override
    final public void onCreate(Activity activity) {
    }

    @Override
    final public void onDestroy(Activity activity) {
    }

    @Override
    final public void onStart(Activity activity) {
    }

    @Override
    final public void onStop(Activity activity) {
    }

    @Override
    abstract public void onResume(Activity activity);

    @Override
    abstract public void onPause(Activity activity);
}
