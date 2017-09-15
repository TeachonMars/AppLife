package com.teachonmars.module.appLife.internal;

import android.app.Activity;

import com.teachonmars.module.appLife.ActivityBaseSpy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WeakableArrayList<T extends ActivityBaseSpy> {
    private ArrayList<WeakReference<T>> weakData = new ArrayList<>();
    private ArrayList<T>                data     = new ArrayList<>();

    public void add(T listener, boolean hardRef) {
        if (listener != null) {
            if (hardRef) {
                data.add(listener.hashCode(), listener);
            } else {
                weakData.add(listener.hashCode(), new WeakReference<>(listener));
            }
        }
    }

    public void remove(ActivityBaseSpy listener) {
        if (listener != null) {
            if (data.get(listener.hashCode()) == listener) {
                data.remove(listener.hashCode());
            } else {
                weakData.remove(listener.hashCode());
            }
        }
    }

    public void iterCreation(Activity activity) {
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

    public void iterDestruction(Activity activity) {
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

    public void iterStart(Activity activity) {
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

    public void iterStop(Activity activity) {
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

    public void iterResume(Activity activity) {
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

    public void iterPause(Activity activity) {
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
