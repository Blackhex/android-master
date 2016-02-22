package com.trinerdis.androidmaster.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.trinerdis.androidmaster.fragment.ViewModelFragment;
import com.trinerdis.androidmaster.view.IExampleFragmentView;

import java.util.Random;

import eu.inloop.viewmodel.AbstractViewModel;

/**
 * Example view-model for {@link ViewModelFragment}.
 */
public class ExampleFragmentViewModel extends AbstractViewModel<IExampleFragmentView>
    implements IExampleFragmentViewModel {

    private static final String TAG = ExampleFragmentViewModel.class.getSimpleName();

    /**
     * Identifiers of view-model arguments.
     */
    public static class Argument {
        public static final String TITLE = "title";
    }

    /**
     * Identifiers of view-model state attributes.
     */
    private static class State {
        public static final String TITLE = "state_title";
        public static final String SQUARE_POSITION_X = "state_position_x";
        public static final String SQUARE_POSITION_Y = "state_position_y";
    }

    private String mTitle;
    private int mSquarePositionX;
    private int mSquarePositionY;

    // region IExampleFragmentViewModel

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
    public int getSquarePositionX() {
        Log.d(TAG, "getSquarePositionX()");

        return mSquarePositionX;
    }

    @Override
    public int getSquarePositionY() {
        Log.d(TAG, "getSquarePositionY()");

        return mSquarePositionY;
    }

    @Override
    public void setSquarePosition(int x, int y) {
        Log.d(TAG, "setSquarePosition()");

        mSquarePositionX = x;
        mSquarePositionY = y;
    }

    // endregion

    // region AbstractViewModel

    @Override
    public void clearView() {
        Log.d(TAG, "clearView()");

        super.clearView();
    }

    @Override
    public void bindView(@NonNull IExampleFragmentView view) {
        Log.d(TAG, "bindView()");

        super.bindView(view);

        // Bind view with view-model.
        view.bindViewModel(this);
        view.setTitle(mTitle);
        view.setSquarePosition(mSquarePositionX, mSquarePositionY);
    }

    @Override
    public void saveState(@NonNull Bundle bundle) {
        Log.d(TAG, "saveState()");

        super.saveState(bundle);

        // Store view-model state attributes.
        bundle.putString(State.TITLE, mTitle);
        bundle.putInt(State.SQUARE_POSITION_X, mSquarePositionX);
        bundle.putInt(State.SQUARE_POSITION_Y, mSquarePositionY);
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
            // Restore view-model state attributes.
            mTitle = savedInstanceState.getString(State.TITLE);
            mSquarePositionX = savedInstanceState.getInt(State.SQUARE_POSITION_X);
            mSquarePositionY = savedInstanceState.getInt(State.SQUARE_POSITION_Y);
        } else {
            // Initialize view-model state attributes.
            mTitle = arguments.getString(Argument.TITLE);
            final Random random = new Random();
            mSquarePositionX = random.nextInt(200);
            mSquarePositionY = random.nextInt(200);
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
