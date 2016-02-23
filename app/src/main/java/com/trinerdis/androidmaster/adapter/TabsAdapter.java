package com.trinerdis.androidmaster.adapter;

import android.os.Bundle;
import android.util.Log;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trinerdis.androidmaster.fragment.DynamicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Constant adapter with example items.
 */
public class TabsAdapter extends FragmentPagerAdapter {

    private static final String TAG = TabsAdapter.class.getSimpleName();

    /**
     * Adapted item model.
     */
    public static class Item {

        public String title;
        public Fragment fragment;

        public Item(String title) {
            this.title = title;

            // Create fragment instance.
            final Bundle arguments = new Bundle();
            arguments.putString(DynamicFragment.Argument.TITLE, title);
            try {
                fragment = new DynamicFragment();
                fragment.setArguments(arguments);
                //fragment.setRetainInstance(true);
            } catch (Exception exception) {
                Log.e(TAG, "Item(): failed to create fragment instance");
            }
        }
    }

    /**
     * Model items in the adapter.
     */
    private final List<Item> mItems = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param manager Fragment manager.
     */
    public TabsAdapter(FragmentManager manager) {
        super(manager);

        for (int i = 0; i < 10; ++i) {
            mItems.add(new Item("Tab " + i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mItems.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).title;
    }
}
