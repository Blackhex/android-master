package com.trinerdis.androidmaster.activity;

import android.util.Log;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.trinerdis.androidmaster.R;

/**
 * API design:
 *
 * https://apiary.io/
 * https://apiblueprint.org/
 * http://editor.swagger.io/
 * https://swaggergo.com
 * https://gelato.io/
 * https://readme.io/
 * https://getsandbox.com/
 * https://apigility.org/
 *
 * HttpURLConnection:
 *
 * http://developer.android.com/reference/java/net/HttpURLConnection.html *
 * http://developer.android.com/about/versions/marshmallow/android-6.0-changes.html
 * https://docs.oracle.com/javase/tutorial/networking/urls/readingWriting.html
 *
 * Volley:
 *
 * http://developer.android.com/training/volley/index.html
 */
public class VolleyActivity extends AppCompatActivity {

    private static final String TAG = VolleyActivity.class.getSimpleName();

    /**
     * Activity views.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_volley);

        // Get fragment views.

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart()");

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause()");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");

        super.onStop();
    }
}
