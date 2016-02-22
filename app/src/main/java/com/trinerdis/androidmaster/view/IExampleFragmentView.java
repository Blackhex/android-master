package com.trinerdis.androidmaster.view;

import com.trinerdis.androidmaster.viewmodel.IExampleFragmentViewModel;

import eu.inloop.viewmodel.IView;

/**
 * Interface for example view-model fragment.
 */
public interface IExampleFragmentView extends IView {

    void bindViewModel(IExampleFragmentViewModel viewModel);

    void setTitle(String title);

    void setSquarePosition(int x, int y);
}
