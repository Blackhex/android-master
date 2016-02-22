package com.trinerdis.androidmaster.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.trinerdis.androidmaster.R;
import com.trinerdis.androidmaster.fragment.ViewModelFragment;
import com.trinerdis.androidmaster.view.IExampleActivityView;
import com.trinerdis.androidmaster.viewmodel.ExampleActivityViewModel;
import com.trinerdis.androidmaster.viewmodel.IExampleActivityViewModel;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;

/**
 * Example of activity with view-model.
 * <p/>
 * https://github.com/inloop/AndroidViewModel
 */
public class ViewModelActivity extends ViewModelBaseActivity<IExampleActivityView,
    ExampleActivityViewModel> implements IExampleActivityView {

    private static final String TAG = ViewModelActivity.class.getSimpleName();

    protected Toolbar mToolbar;
    protected Button mReplaceButton;
    protected Button mAddRemoveButton;

    // region IExampleActivityView

    @Override
    public void bindViewModel(IExampleActivityViewModel viewModel) {
        Log.d(TAG, "bindViewModel()");

        // Bind buttons on click events.
        mReplaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onReplaceButtonClick()");

                // Check if view is bound.
                final ExampleActivityViewModel viewModel = getViewModel();
                if (viewModel == null) {
                    Log.e(TAG, "onReplaceButtonClick(): view is not bound to view-model");
                    return;
                }

                viewModel.onReplaceButtonClick();
            }
        });
        mAddRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onAddRemoveButtonClick()");

                // Check if view is bound.
                final ExampleActivityViewModel viewModel = getViewModel();
                if (viewModel == null) {
                    Log.e(TAG, "onAddRemoveButtonClick(): view is not bound to view-model");
                    return;
                }

                viewModel.onAddRemoveButtonClick();
            }
        });
    }

    @Override
    public void setTitle(String title) {
        Log.d(TAG, "setTitle()");

        mToolbar.setTitle(title);
    }

    public void addFragment(String title) {
        Log.d(TAG, "addFragment()");

        // Create fragment instance.
        Fragment fragment = createFragment(title);

        // Add it to layout.
        getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }

    @Override
    public void replaceFragment(String title) {
        Log.d(TAG, "replaceFragment()");

        // Create another fragment instance.
        Fragment fragment = createFragment(title);

        // Replace it in layout.
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
    }

    @Override
    public void addRemoveFragment(String title) {
        Log.d(TAG, "addRemoveFragment()");

        // Create another fragment instance.
        Fragment fragment = createFragment(title);

        // Replace it in layout.
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
    }

    // endregion

    // region ViewModelBaseActivity

    @Override
    public Class<ExampleActivityViewModel> getViewModelClass() {
        return ExampleActivityViewModel.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);

        // Set activity layout.
        setContentView(R.layout.activity_view_model);

        // Get activity views.
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mReplaceButton = (Button) findViewById(R.id.replace_button);
        mAddRemoveButton = (Button) findViewById(R.id.add_remove_button);

        // Set view to view-model. Must be called after the view references are initialized
        // because view model will set their content.
        setModelView(this);
    }

    // endregion

    private Fragment createFragment(String title) {
        Log.d(TAG, "createFragment(): title: " + title);

        //return DynamicFragment.newInstance(title);
        return ViewModelFragment.newInstance(title);
    }
}
