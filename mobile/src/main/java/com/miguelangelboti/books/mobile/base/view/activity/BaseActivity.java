package com.miguelangelboti.books.mobile.base.view.activity;

import android.app.Activity;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.miguelangelboti.books.mobile.BooksApplication;
import com.miguelangelboti.books.mobile.di.components.ApplicationComponent;
import com.miguelangelboti.books.mobile.di.modules.ActivityModule;

/**
 * A simple {@link AppCompatActivity} subclass.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Get the Main Application component for dependency injection.
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((BooksApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * Reverses the Activity Scene entry Transition and triggers the calling Activity to reverse its exit Transition.
     * When the exit Transition completes, {@link #finish()} is called. If no entry Transition was used, finish() is
     * called immediately and the Activity exit Transition is run.
     * @see android.app.ActivityOptions#makeSceneTransitionAnimation(Activity, android.util.Pair[])
     */
    public void finishWithAnimation() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }

    /**
     * Shows a message as SnackBar.
     * @param message An string representing a message to be shown.
     */
    protected void showSnackMessage(String message) {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        }
    }
}
