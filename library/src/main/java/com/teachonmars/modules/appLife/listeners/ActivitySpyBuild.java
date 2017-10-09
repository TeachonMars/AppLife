package com.teachonmars.modules.appLife.listeners;

import android.app.Activity;

abstract public class ActivitySpyBuild implements ActivitySpyBase {
    @Override
    abstract public void onCreate(Activity activity);

    @Override
    abstract public void onDestroy(Activity activity);

    @Override
    final public void onStart(Activity activity) {
    }

    @Override
    final public void onStop(Activity activity) {
    }

    @Override
    final public void onResume(Activity activity) {
    }

    @Override
    final public void onPause(Activity activity) {
    }
}
