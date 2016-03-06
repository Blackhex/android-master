package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

public class RoboSpiceActivity extends AppCompatActivity {

    private static final String TAG = RoboSpiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_robo_spice);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
