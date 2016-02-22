package com.trinerdis.androidmaster.viewmodel;

import com.trinerdis.androidmaster.activity.ViewModelActivity;

/**
 * Example view-model for {@link ViewModelActivity}.
 */
public interface IExampleActivityViewModel {

    String getTitle();

    void setTitle(String title);

    String getFragmentTitle();

    void onReplaceButtonClick();

    void onAddRemoveButtonClick();
}
