package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

public class RenderingActivity extends AppCompatActivity {

    private static final String TAG = RenderingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_rendering);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
