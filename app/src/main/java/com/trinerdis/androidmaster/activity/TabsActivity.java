package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.adapter.TabsAdapter;

/**
 * http://www.androidhive.info/2015/09/android-material-design-working-with-tabs/
 * https://www.google.com/design/spec/components/tabs.html
 * http://www.android4devs.com/2015/12/tab-layout-material-design-support.html
 */
public class TabsActivity extends AppCompatActivity {

    private static final String TAG = TabsActivity.class.getSimpleName();

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_tabs);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        // Create adapter and connect it with TabLayout and ViewPager.
        mViewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
