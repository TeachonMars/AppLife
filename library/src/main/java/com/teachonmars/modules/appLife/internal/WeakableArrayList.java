package com.teachonmars.modules.appLife.internal;

import android.app.Activity;

import com.teachonmars.modules.appLife.listeners.ActivitySpyBase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

class WeakableArrayList<T extends ActivitySpyBase> {
    private ArrayList<WeakReference<T>> weakData = new ArrayList<>();
    private ArrayList<T>                data     = new ArrayList<>();

    void add(T listener, boolean hardRef) {
        if (listener != null) {
            if (hardRef) {
                data.add(listener.hashCode(), listener);
            } else {
                weakData.add(listener.hashCode(), new WeakReference<>(listener));
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
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onCreate(activity);
            }
        }
        for (T listener : data) {
            listener.onCreate(activity);
        }

    }

    void iterDestruction(Activity activity) {
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onDestroy(activity);
            }
        }
        for (T listener : data) {
            listener.onDestroy(activity);
        }
    }

    void iterStart(Activity activity) {
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onStart(activity);
            }
        }
        for (T listener : data) {
            listener.onStart(activity);
        }
    }

    void iterStop(Activity activity) {
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onStop(activity);
            }
        }
        for (T listener : data) {
            listener.onStop(activity);
        }
    }

    void iterResume(Activity activity) {
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onResume(activity);
            }
        }
        for (T listener : data) {
            listener.onResume(activity);
        }
    }

    void iterPause(Activity activity) {
        for (WeakReference<T> listenerRef : weakData) {
            T listener = listenerRef.get();
            if (listener != null) {
                listener.onPause(activity);
            }
        }
        for (T listener : data) {
            listener.onPause(activity);
        }
    }
}
