package com.trinerdis.androidmaster.fragment;

import java.util.Arrays;

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
import com.trinerdis.androidmaster.adapter.DividerItemDecoration;
import com.trinerdis.androidmaster.adapter.MessagesAdapter;
import com.trinerdis.androidmaster.api.Message;

/**
 * Fragment with list of messages.
 */
public class MessagesFragment extends Fragment {

    private static final String TAG = MessagesFragment.class.getSimpleName();

    /**
     * Items adapter.
     */
    protected MessagesAdapter mAdapter;

    /**
     * Base URL of application API.
     */
    protected String mApiUrl;

    /**
     * Fragment views.
     */
    protected RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        // Create fragment layout.
        final View root = inflater.inflate(R.layout.fragment_messages, container, false);

        // Get fragment views.
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        // Load resources.
        mApiUrl = getString(R.string.api_url);

        // Configure recycler view.
        final Activity activity = getActivity();
        mAdapter = new MessagesAdapter(activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(activity));
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    protected String getMessagesUrl() {
        return mApiUrl + "messages";
    }

    protected void showMessages(Message[] messages) {
        Log.d(TAG, "showMessages()");

        // Update RecyclerView adapter.
        mAdapter.clear();
        mAdapter.addAll(Arrays.asList(messages));
        mAdapter.notifyDataSetChanged();
    }
}
