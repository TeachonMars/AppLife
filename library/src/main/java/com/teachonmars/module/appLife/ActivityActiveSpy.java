package com.teachonmars.module.appLife;

import android.app.Activity;

abstract public class ActivityActiveSpy implements ActivityBaseSpy {
    @Override
    final public void onCreate(Activity activity) {
    }

    @Override
    public void onDestroy(Activity activity) {
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
