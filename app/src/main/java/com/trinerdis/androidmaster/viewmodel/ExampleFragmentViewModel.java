package com.trinerdis.androidmaster.viewmodel;

import com.trinerdis.androidmaster.fragment.ViewModelFragment;
import com.trinerdis.androidmaster.view.IExampleFragmentView;

import eu.inloop.viewmodel.AbstractViewModel;

/**
 * Example view-model for {@link ViewModelFragment}.
 */
public class ExampleFragmentViewModel extends AbstractViewModel<IExampleFragmentView>
    implements IExampleFragmentViewModel {

    private static final String TAG = ExampleFragmentViewModel.class.getSimpleName();

    @Override
    public String getTitle() {
        // TODO
        return null;
    }

    @Override
    public void setTitle(String title) {
        // TODO
    }

    @Override
    public int getSquarePositionX() {
        // TODO
        return 0;
    }

    @Override
    public int getSquarePositionY() {
        // TODO
        return 0;
    }

    @Override
    public int setSquarePosition(int x, int y) {
        // TODO
        return 0;
    }

    /**
     * Identifiers of view-model state attributes.
     */
    private static class State {
        public static final String TITLE = "state_title";
        public static final String SQUARE_POSITION_X = "state_square_position_x";
        public static final String SQUARE_POSITION_Y = "state_square_position_y";
    }
}
