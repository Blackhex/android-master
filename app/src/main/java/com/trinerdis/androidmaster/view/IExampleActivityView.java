package com.trinerdis.androidmaster.view;

import com.trinerdis.androidmaster.viewmodel.IExampleActivityViewModel;

import eu.inloop.viewmodel.IView;

/**
 * Interface for example view-model activity.
 */
public interface IExampleActivityView extends IView {

    void bindViewModel(IExampleActivityViewModel viewModel);

    void setTitle(String title);

    void addFragment(String title);

    void replaceFragment(String title);

    void addRemoveFragment(String title);
}
