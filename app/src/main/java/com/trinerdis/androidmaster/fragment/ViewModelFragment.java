package com.trinerdis.androidmaster.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.view.IExampleFragmentView;
import com.trinerdis.androidmaster.viewmodel.ExampleFragmentViewModel;
import com.trinerdis.androidmaster.viewmodel.IExampleFragmentViewModel;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;

/**
 * Example of fragment with view-model.
 * <p/>
 * https://github.com/inloop/AndroidViewModel
 */
public class ViewModelFragment extends ViewModelBaseFragment<IExampleFragmentView,
    ExampleFragmentViewModel> implements IExampleFragmentView {

    private final String TAG = ViewModelFragment.class.getSimpleName();

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
    public static ViewModelFragment newInstance(String title) {

        // Create fragment instance.
        final ViewModelFragment instance = new ViewModelFragment();

        // Create and set its arguments.
        final Bundle arguments = new Bundle();
        arguments.putString(ExampleFragmentViewModel.Argument.TITLE, title);
        instance.setArguments(arguments);

        return instance;
    }

    // region IExampleFragmentView

    @Override
    public void bindViewModel(IExampleFragmentViewModel viewModel) {
        Log.d(TAG, "bindViewModel()");

        // Nothing to bind.
    }

    @Override
    public void setTitle(String title) {
        Log.d(TAG, "setTitle()");

        mTitleTextView.setText(title);
    }

    @Override
    public void setSquarePosition(int x, int y) {
        Log.d(TAG, "setSquareTitle()");

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mSquareImageView
            .getLayoutParams();
        params.leftMargin = x;
        params.topMargin = y;
        mSquareImageView.setLayoutParams(params);
    }

    // endregion

    // region  ViewModelBaseFragment

    @Nullable
    @Override
    public Class<ExampleFragmentViewModel> getViewModelClass() {
        return ExampleFragmentViewModel.class;
    }

    // endregion

    // region Fragment

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach()");

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
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

        // Set view to view-model. Must be called after the view references are initialized
        // because view model will set their content.
        setModelView(this);

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState()");

        super.onSaveInstanceState(outState);
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

    // endregion
}
