package com.teachonmars.modules.appLife.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teachonmars.modules.appLife.listeners.ActivitySpyBase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.ListIterator;

public final class AppSpy implements Application.ActivityLifecycleCallbacks {


    private LinkedHashMap<Integer, WeakReference<Activity>> activityStack           = new LinkedHashMap<>();
    private WeakableArrayList<ActivitySpyBase>              activitiesLifeListeners = new WeakableArrayList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        bindActivity(activity);
        activitiesLifeListeners.iterCreation(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        activitiesLifeListeners.iterStart(activity);

    }

    @Override
    public void onActivityResumed(Activity activity) {
        activitiesLifeListeners.iterResume(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        activitiesLifeListeners.iterPause(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activitiesLifeListeners.iterStop(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        unbindActivity(activity);
        activitiesLifeListeners.iterDestruction(activity);
    }

    @Nullable
    public Activity tryGetCurrentActivity() {
        Collection<WeakReference<Activity>> values = activityStack.values();
        ListIterator<WeakReference<Activity>> weakReferenceListIterator = new ArrayList<>(values).listIterator(values.size());
        while (weakReferenceListIterator.hasPrevious()) {
            WeakReference<Activity> activityRef = weakReferenceListIterator.previous();
            Activity candidate = ensureActivityStillExist(activityRef.get());
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }

    private Activity ensureActivityStillExist(Activity activity) {
        if (activity != null
                && Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1
                && activity.isDestroyed()) {
            return null;
        } else {
            return activity;
        }
    }

    private void bindActivity(Activity activity) {
        if (activity != null) {
            activityStack.put(activity.hashCode(), new WeakReference<>(activity));
        }
    }

    private void unbindActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity.hashCode());
        }
    }

    public boolean isStillAlive(int activityHash) {
        return ensureActivityStillExist(activityStack.get(activityHash).get())!=null;
    }

    public void registerListener(ActivitySpyBase listener, boolean hardRef) {
        if (listener != null) {
            activitiesLifeListeners.add(listener, hardRef);
        }
    }

    public void unregisterListener(ActivitySpyBase listener) {
        if (listener != null) {
            activitiesLifeListeners.remove(listener);
        }
    }
}
