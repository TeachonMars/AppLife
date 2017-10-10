package com.teachonmars.modules.appLife.listeners;

import android.app.Activity;

/**
 * Derived abstract class used to follow only activity's lifecycle is build (onCreate &amp; onDestroy)
 */
abstract public class ActivitySpyBuild implements ActivitySpyBase {
    @Override
    abstract public void onCreate(Activity activity);

    @Override
    final public void onStart(Activity activity) {
    }

    @Override
    final public void onResume(Activity activity) {
    }

    @Override
    final public void onPause(Activity activity) {
    }

    @Override
    final public void onStop(Activity activity) {
    }

    @Override
    abstract public void onDestroy(Activity activity);

}
