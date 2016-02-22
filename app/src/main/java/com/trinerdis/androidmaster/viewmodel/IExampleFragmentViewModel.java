package com.trinerdis.androidmaster.viewmodel;

import com.trinerdis.androidmaster.fragment.ViewModelFragment;

/**
 * Example view-model for {@link ViewModelFragment}.
 */
public interface IExampleFragmentViewModel {

    String getTitle();

    void setTitle(String title);

    int getSquarePositionX();

    int getSquarePositionY();

    void setSquarePosition(int x, int y);
}
