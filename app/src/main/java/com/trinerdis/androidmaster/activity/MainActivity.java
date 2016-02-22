package com.trinerdis.androidmaster.activity;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.adapter.DividerItemDecoration;
import com.trinerdis.androidmaster.adapter.ExamplesAdapter;

/**
 * Main application activity with list of examples.
 *
 * http://developer.android.com/training/material/lists-cards.html
 * http://developer.android.com/reference/android/support/v7/widget/RecyclerView.html
 * http://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html
 * http://javatechig.com/android/android-recyclerview-example
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected Toolbar mToolbar;
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_main);

        // Inject activity views.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Set a toolbar to replace the action bar.
        setSupportActionBar(mToolbar);

        // Enable toolbar shadow on pre-Lollipop devices.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            final Resources resources = getResources();
            final DisplayMetrics display = resources.getDisplayMetrics();

            ViewCompat.setElevation(mToolbar, 4.0f * display.density);
        }

        // Configure recycler view.
        final RecyclerView.Adapter adapter = new ExamplesAdapter(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}