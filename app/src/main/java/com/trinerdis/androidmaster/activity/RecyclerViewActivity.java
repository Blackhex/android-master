package com.trinerdis.androidmaster.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trinerdis.androidmaster.R;

/**
 * Activity with RecyclerViewFragment.
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String TAG = RecyclerViewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_recycler_view);
    }
}
