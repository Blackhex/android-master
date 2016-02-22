package com.trinerdis.androidmaster;

import android.app.Application;
import android.util.Log;

/**
 * Application class.
 */
public class AndroidMaster extends Application {

    private static final String TAG = "AndroidMaster";

    /**
     * Application singleton instance.
     */
    private static AndroidMaster mInstance;

    public static String getResourceString(int resourceId) {
        return mInstance.getString(resourceId);
    }

    public static String getResourceString(int resourceId, Object... parameters) {
        return mInstance.getString(resourceId, parameters);
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");

        super.onCreate();

        mInstance = this;
    }
}
