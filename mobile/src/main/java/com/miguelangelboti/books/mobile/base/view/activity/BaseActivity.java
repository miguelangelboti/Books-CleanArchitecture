package com.miguelangelboti.books.mobile.base.view.activity;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * A simple {@link AppCompatActivity} subclass.
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Reverses the Activity Scene entry Transition and triggers the calling Activity to reverse its
     * exit Transition. When the exit Transition completes, {@link #finish()} is called. If no entry
     * Transition was used, finish() is called immediately and the Activity exit Transition is run.
     * @see android.app.ActivityOptions#makeSceneTransitionAnimation(Activity, android.util.Pair[])
     */
    public void finishWithAnimation() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            finish();
        }
    }
}
