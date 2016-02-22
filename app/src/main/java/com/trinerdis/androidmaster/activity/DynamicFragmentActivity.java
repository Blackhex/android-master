package com.trinerdis.androidmaster.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.fragment.DynamicFragment;

public class DynamicFragmentActivity extends AppCompatActivity {

    private static final String TAG = DynamicFragmentActivity.class.getSimpleName();

    private static class State {
        private static final String ID = "state_id";
    }

    /**
     * Some activity state member.
     */
    protected int mId = 1;

    /**
     * Link to currently displayed fragment.
     */
    protected Fragment mFragment;

    /**
     * Activity views.
     */
    protected Button mReplaceButton;
    protected Button mAddRemoveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Must call super.
        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_dynamic_fragment);

        // Get fragment views.
        mReplaceButton = (Button) findViewById(R.id.replace_button);
        mAddRemoveButton = (Button) findViewById(R.id.add_remove_button);

        // Restore fragment state if any.
        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate(): saved instance");

            restoreInstance(savedInstanceState);
        } else {
            Log.d(TAG, "onCreate(): new instance");
        }

        // Try to find the retained fragment on activity restarts.
        /*FragmentManager manager = getSupportFragmentManager();
        mFragment = manager.findFragmentByTag(TAG);*/

        Log.d(TAG, "onCreate(): id: " + mId + ", fragment: " + mFragment);

        if (mFragment == null) {
            // Create fragment instance.
            mFragment = createFragment();

            // Add fragment to to layout.
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mFragment, TAG)
                .commit();
        }

        // Setup on click listeners.
        mReplaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReplaceButtonClick();
            }
        });
        mAddRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddRemoveButtonClick();
            }
        });
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
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent()");

        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");

        super.onSaveInstanceState(outState);

        saveInstance(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "onRestoreInstanceState()");

        super.onRestoreInstanceState(savedInstanceState);

        restoreInstance(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");

        super.onConfigurationChanged(newConfig);
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

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();
    }

    private Fragment createFragment() {
        Log.d(TAG, "createFragment(): id: " + mId);

        final String title = getString(R.string.dynamic_fragment_title_pattern, mId++);
        final Fragment fragment = DynamicFragment.newInstance(title);
        //fragment.setRetainInstance(true);
        return fragment;
    }

    private void saveInstance(Bundle outState) {
        Log.d(TAG, "saveInstance()");

        outState.putInt(State.ID, mId);
    }

    private void restoreInstance(Bundle savedInstanceState) {
        Log.d(TAG, "restoreInstance()");

        mId = savedInstanceState.getInt(State.ID);
    }

    private void onReplaceButtonClick() {
        Log.d(TAG, "onReplaceButtonClick()");

        // Create another fragment instance.
        mFragment = createFragment();

        // Replace fragment in layout.
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, mFragment)
            .commit();
    }

    private void onAddRemoveButtonClick() {
        Log.d(TAG, "onAddRemoveButtonClick()");

        // Create another fragment instance.
        final Fragment oldFragment = mFragment;
        mFragment = createFragment();

        // Replace fragment in layout.
        getSupportFragmentManager()
            .beginTransaction()
            .remove(oldFragment)
            .replace(R.id.fragment_container, mFragment, TAG)
            .commit();
    }
}
