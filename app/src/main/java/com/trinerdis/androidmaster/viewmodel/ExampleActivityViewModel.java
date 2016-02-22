package com.trinerdis.androidmaster.viewmodel;

import android.os.Bundle;
import android.util.Log;

import android.support.annotation.NonNull;

import com.trinerdis.androidmaster.AndroidMaster;
import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.activity.ViewModelActivity;
import com.trinerdis.androidmaster.view.IExampleActivityView;

import eu.inloop.viewmodel.AbstractViewModel;

/**
 * Example view-model for {@link ViewModelActivity}.
 */
public class ExampleActivityViewModel extends AbstractViewModel<IExampleActivityView>
    implements IExampleActivityViewModel {

    private static final String TAG = ExampleActivityViewModel.class.getSimpleName();

    /**
     * Identifiers of view-model state attributes.
     */
    private static class State {
        public static final String ID = "state_id";
        public static final String TITLE = "state_title";
    }

    /**
     * View-model state attributes.
     */
    private int mId;
    private String mTitle;

    // region IExampleActivityViewModel

    @Override
    public String getTitle() {
        Log.d(TAG, "getTitle()");

        return mTitle;
    }

    @Override
    public void setTitle(String title) {
        Log.d(TAG, "setTitle()");

        mTitle = title;
    }

    @Override
    public String getFragmentTitle() {
        return AndroidMaster.getResourceString(R.string.dynamic_fragment_title_pattern, mId);
    }

    @Override
    public void onAddRemoveButtonClick() {
        Log.d(TAG, "onAddRemoveButtonClick()");

        // Check if view is bound.
        final IExampleActivityView view = getView();
        if (view == null) {
            Log.e(TAG, "onReplaceButtonClick(): view is not bound to view-model");
            return;
        }

        // Generate the next fragment identifier.
        mId++;

        view.addRemoveFragment(getFragmentTitle());
    }

    @Override
    public void onReplaceButtonClick() {
        Log.d(TAG, "onReplaceButtonClick()");

        // Check if view is bound.
        final IExampleActivityView view = getView();
        if (view == null) {
            Log.e(TAG, "onReplaceButtonClick(): view is not bound to view-model");
            return;
        }

        // Generate the next fragment identifier.
        mId++;

        view.replaceFragment(getFragmentTitle());
    }

    // endregion

    // region AbstractViewModel

    @Override
    public void clearView() {
        Log.d(TAG, "clearView()");

        super.clearView();
    }

    @Override
    public void bindView(@NonNull IExampleActivityView view) {
        Log.d(TAG, "bindView()");

        super.bindView(view);

        view.bindViewModel(this);
        view.setTitle(mTitle);
        view.replaceFragment(getFragmentTitle());
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {
        Log.d(TAG, "saveState()");

        super.saveState(bundle);

        bundle.putInt(State.ID, mId);
        bundle.putString(State.TITLE, mTitle);
    }

    @Override
    public void onModelRemoved() {
        Log.d(TAG, "onModelRemoved()");

        super.onModelRemoved();
    }

    @Override
    public void onCreate(Bundle arguments, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            // Restore view-model properties.
            mId = savedInstanceState.getInt(State.ID);
            mTitle = savedInstanceState.getString(State.TITLE);
        } else {
            // Initialize view-model properties.
            mId = 1;
            mTitle = AndroidMaster.getResourceString(R.string.view_model_activity_title);
        }
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart()");

        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop()");

        super.onStop();
    }

    // endregion
}
