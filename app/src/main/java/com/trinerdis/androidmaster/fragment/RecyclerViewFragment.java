package com.trinerdis.androidmaster.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.adapter.HeterogeneousAdapter;

/**
 * Fragment with recycler view.
 *
 * http://developer.android.com/training/material/lists-cards.html
 * https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView
 */
public class RecyclerViewFragment extends Fragment {

    private static final String TAG = RecyclerViewFragment.class.getSimpleName();

    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        // Create fragment layout.
        final View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        // Get fragment views.
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        // Configure recycler view.
        final Activity activity = getActivity();
        final RecyclerView.Adapter adapter = new HeterogeneousAdapter(activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.setAdapter(adapter);

        return root;
    }
}
