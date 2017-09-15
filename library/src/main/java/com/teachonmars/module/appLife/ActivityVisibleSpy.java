package com.teachonmars.module.appLife;

import android.app.Activity;

abstract public class ActivityVisibleSpy implements ActivityBaseSpy {
    @Override
    final public void onCreate(Activity activity) {
    }

    @Override
    final public void onDestroy(Activity activity) {
    }

    @Override
    abstract public void onStart(Activity activity);

    @Override
    abstract public void onStop(Activity activity);

    @Override
    final public void onResume(Activity activity) {
    }

    @Override
    final public void onPause(Activity activity) {
    }
}
