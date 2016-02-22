package com.trinerdis.androidmaster.view;

import eu.inloop.viewmodel.IView;

/**
 * Interface for example view-model fragment.
 */
public interface IExampleFragmentView extends IView {

    void setTitle(String title);

    void setSquarePosition(int x, int y);
}
