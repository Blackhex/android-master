package com.trinerdis.androidmaster.activity;

import android.util.Log;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

import com.trinerdis.androidmaster.R;

public class VolleyActivity extends AppCompatActivity {

    private static final String TAG = VolleyActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_volley);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
