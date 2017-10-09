package com.teachonmars.modules.appLife.listeners;

import android.app.Activity;

public interface ActivitySpyBase {
    void onCreate(Activity activity);

    void onDestroy(Activity activity);

    void onStart(Activity activity);

    void onStop(Activity activity);

    void onResume(Activity activity);

    void onPause(Activity activity);
}
