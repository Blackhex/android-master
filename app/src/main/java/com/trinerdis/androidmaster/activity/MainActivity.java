package com.trinerdis.androidmaster.activity;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(Tag, "onCreate()");

        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_main);

        // Set a toolbar to replace the action bar.
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable toolbar shadow on pre-Lollipop devices.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            final Resources resources = getResources();
            final DisplayMetrics display = resources.getDisplayMetrics();

            ViewCompat.setElevation(toolbar, 4.0f * display.density);
        }
    }
}