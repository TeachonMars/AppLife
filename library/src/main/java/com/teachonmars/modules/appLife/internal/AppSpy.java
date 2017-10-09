package com.teachonmars.modules.appLife.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.teachonmars.modules.appLife.listeners.ActivityActiveSpy;
import com.teachonmars.modules.appLife.listeners.ActivityBaseSpy;
import com.teachonmars.modules.appLife.listeners.ActivityCreationSpy;
import com.teachonmars.modules.appLife.listeners.ActivityVisibleSpy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.ListIterator;

public final class AppSpy implements Application.ActivityLifecycleCallbacks {


    private LinkedHashMap<Integer, WeakReference<Activity>> activityStack              = new LinkedHashMap<>();
    private WeakableArrayList<ActivityCreationSpy>          creationActivityListeners  = new WeakableArrayList<>();
    private WeakableArrayList<ActivityVisibleSpy>           visibleActivityListeners   = new WeakableArrayList<>();
    private WeakableArrayList<ActivityActiveSpy>            activeActivityListeners    = new WeakableArrayList<>();
    private WeakableArrayList<ActivityBaseSpy>              wholeActivityLifeListeners = new WeakableArrayList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        bindActivity(activity);
        creationActivityListeners.iterCreation(activity);
        wholeActivityLifeListeners.iterCreation(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        visibleActivityListeners.iterStart(activity);
        wholeActivityLifeListeners.iterStart(activity);

    }

    @Override
    public void onActivityResumed(Activity activity) {
        activeActivityListeners.iterResume(activity);
        wholeActivityLifeListeners.iterResume(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        activeActivityListeners.iterPause(activity);
        wholeActivityLifeListeners.iterPause(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        visibleActivityListeners.iterStop(activity);
        wholeActivityLifeListeners.iterStop(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        unbindActivity(activity);
        creationActivityListeners.iterDestruction(activity);
        wholeActivityLifeListeners.iterDestruction(activity);
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
        return activityStack.get(activityHash).get() != null;
    }

    public void registerListener(ActivityBaseSpy listener, boolean hardRef) {
        if (listener != null) {
            if (listener instanceof ActivityCreationSpy) {
                creationActivityListeners.add((ActivityCreationSpy) listener, hardRef);
            } else if (listener instanceof ActivityVisibleSpy) {
                visibleActivityListeners.add((ActivityVisibleSpy) listener, hardRef);
            } else if (listener instanceof ActivityActiveSpy) {
                activeActivityListeners.add((ActivityActiveSpy) listener, hardRef);
            } else {
                wholeActivityLifeListeners.add(listener, hardRef);
            }
        }
    }

    public void unregisterListener(ActivityBaseSpy listener) {
        if (listener != null) {
            if (listener instanceof ActivityCreationSpy) {
                creationActivityListeners.remove(listener);
            } else if (listener instanceof ActivityVisibleSpy) {
                visibleActivityListeners.remove(listener);
            } else if (listener instanceof ActivityActiveSpy) {
                activeActivityListeners.remove(listener);
            } else {
                wholeActivityLifeListeners.remove(listener);
            }
        }
    }

//    public void registerListener(Application.ActivityLifecycleCallbacks listener) {
//    }
//
//    public void unregisterListener(Application.ActivityLifecycleCallbacks listener) {
//
//    }
}
