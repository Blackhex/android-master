package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.util.Log;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = RetrofitActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_retrofit);

        // Override action bar.
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }
}
