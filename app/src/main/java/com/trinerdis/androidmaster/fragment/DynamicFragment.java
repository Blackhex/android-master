package com.trinerdis.androidmaster.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.support.v4.app.Fragment;

import com.trinerdis.androidmaster.R;

import java.util.Random;

/**
 * Fragment for DynamicFragmentActivity.
 *
 * http://developer.android.com/guide/components/activities.html
 * http://developer.android.com/guide/components/fragments.html
 * http://developer.android.com/guide/topics/resources/runtime-changes.html
 */
public class DynamicFragment extends Fragment {

    private final String TAG = DynamicFragment.class.getSimpleName();

    public static class Argument {
        public static final String TITLE = "title";
    }

    private static class State {
        public static final String TITLE = "state_title";
    }

    /**
     * Title as a fragment state member.
     */
    protected String mTitle;

    /**
     * Fragment views.
     */
    protected TextView mTitleTextView;
    protected ImageView mSquareImageView;

    /**
     * Use static factory method for creating fragments using {@link Bundle}. Do not create
     * parametrized fragments directly using constructor.
     *
     * @param title Fragment title text.
     * @return {@link DynamicFragment} instance.
     */
    public static DynamicFragment newInstance(String title) {

        // Create fragment instance.
        final DynamicFragment instance = new DynamicFragment();

        // Create and set its arguments.
        final Bundle arguments = new Bundle();
        arguments.putString(Argument.TITLE, title);
        instance.setArguments(arguments);

        return instance;
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach()");

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate(): saved instance");

            restoreInstance(savedInstanceState);
        } else {
            Log.d(TAG, "onCreate(): new instance");

            loadArguments(getArguments());
        }

        Log.d(TAG, "onCreate(): title: " + mTitle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView()");

        // Create fragment layout.
        final View root = inflater.inflate(R.layout.fragment_dynamic, container, false);

        // Get fragment views.
        mTitleTextView = (TextView) root.findViewById(R.id.title_text_view);
        mSquareImageView = (ImageView) root.findViewById(R.id.square_image_view);

        // Set fragment title.
        mTitleTextView.setText(mTitle);

        // Randomize image position.
        final Random random = new Random();
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSquareImageView
            .getLayoutParams();
        params.leftMargin = random.nextInt(200);
        params.topMargin = random.nextInt(200);
        mSquareImageView.setLayoutParams(params);
        mSquareImageView.setVisibility(View.VISIBLE);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated()");

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");

        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart()");

        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");

        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");

        super.onSaveInstanceState(outState);

        saveInstance(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");

        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause()");

        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView()");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach()");

        super.onDetach();
    }

    private void loadArguments(Bundle arguments) {
        Log.d(TAG, "loadArguments()");

        mTitle = arguments.getString(Argument.TITLE);
    }

    private void saveInstance(Bundle outState) {
        Log.d(TAG, "saveInstance()");

        outState.putString(State.TITLE, mTitle);
    }

    private void restoreInstance(Bundle savedInstanceState) {
        Log.d(TAG, "restoreInstance()");

        mTitle = savedInstanceState.getString(State.TITLE);
    }
}
