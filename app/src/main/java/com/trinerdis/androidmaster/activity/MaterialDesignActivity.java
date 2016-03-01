package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.trinerdis.androidmaster.R;

/**
 *
 */
public class MaterialDesignActivity extends AppCompatActivity {

    private static final String TAG = MaterialDesignActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_material_design);
    }
}
