package com.teachonmars.module.appLife;

import android.app.Activity;

public interface ActivityBaseSpy {
    void onCreate(Activity activity);

    void onDestroy(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);
}
