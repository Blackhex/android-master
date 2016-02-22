package com.trinerdis.androidmaster.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trinerdis.androidmaster.view.IExampleFragmentView;
import com.trinerdis.androidmaster.viewmodel.ExampleFragmentViewModel;

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
     * Identifiers of fragment arguments.
     */
    public static class Argument {
        public static final String TITLE = "title";
    }

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
        arguments.putString(Argument.TITLE, title);
        instance.setArguments(arguments);

        return instance;
    }

    // region IExampleFragmentView

    @Override
    public void setTitle(String title) {
        // TODO
    }

    @Override
    public void setSquarePosition(int x, int y) {
        // TODO
    }

    // endregion

    // region  ViewModelBaseFragment

    @Nullable
    @Override
    public Class<ExampleFragmentViewModel> getViewModelClass() {
        return null;
    }

    // endregion
}
