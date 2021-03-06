package com.teachonmars.modules.appLife.internal;

import android.app.Activity;

import com.teachonmars.modules.appLife.listeners.ActivitySpyBase;

import java.lang.ref.WeakReference;
import java.util.Hashtable;


/**
 * Pseudo ArrayList can be used to store data items in ArrayList directly or by using WeakReference for each item
 *
 * @param <T> An implementation of {@link ActivitySpyBase} or derived ({@link com.teachonmars.modules.appLife.listeners.ActivitySpyBuild},
 *            {@link com.teachonmars.modules.appLife.listeners.ActivitySpyLive}, {@link com.teachonmars.modules.appLife.listeners.ActivitySpyVisible})
 */
class WeakableList<T extends ActivitySpyBase> {
    private Hashtable<Integer, WeakReference<T>> weakData = new Hashtable<>();
    private Hashtable<Integer, T>                data     = new Hashtable<>();

    void add(T listener, boolean hardRef) {
        if (listener != null) {
            if (hardRef) {
                data.put(listener.hashCode(), listener);
            } else {
                weakData.put(listener.hashCode(), new WeakReference<>(listener));
            }
        }
    }

    void remove(ActivitySpyBase listener) {
        if (listener != null) {
            if (data.get(listener.hashCode()) == listener) {
                data.remove(listener.hashCode());
            } else {
                weakData.remove(listener.hashCode());
            }
        }
    }

    void iterCreation(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onCreate(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onCreate(activity);
        }

    }

    void iterDestruction(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onDestroy(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onDestroy(activity);
        }
    }

    void iterStart(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onStart(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onStart(activity);
        }
    }

    void iterStop(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onStop(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onStop(activity);
        }
    }

    void iterResume(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onResume(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onResume(activity);
        }
    }

    void iterPause(Activity activity) {
        for (WeakReference<T> listenerRef : weakData.values()) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onPause(activity);
            }
        }
        for (T listener : data.values()) {
            listener.onPause(activity);
        }
    }
}
