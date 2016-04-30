package com.miguelangelboti.books.mobile;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.Checkable;

/**
 * @author Miguel Ángel Botija.
 */
public class CheckableFloatingActionButton extends FloatingActionButton implements Checkable {

    /**
     * Interface definition for a callback to be invoked when the checked state of a compound button changes.
     */
    public interface OnCheckedChangeListener {

        /**
         * Called when the checked state of a FAB has changed.
         * @param fabView The FAB view whose state has changed.
         * @param isChecked The new checked state of buttonView.
         */
        void onCheckedChanged(FloatingActionButton fabView, boolean isChecked);
    }

    /**
     * An array of states.
     */
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    /**
     * A boolean that tells if the FAB is checked or not.
     */
    private boolean checked;

    /**
     * A listener to communicate that the FAB has changed it's state.
     */
    private OnCheckedChangeListener onCheckedChangeListener;

    public CheckableFloatingActionButton(Context context) {
        this(context, null, 0);
    }

    public CheckableFloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckableFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }

    @Override
    public void setChecked(boolean checked) {
        setChecked(checked, false);
    }

    /**
     * Sets the checked/unchecked state of the FAB with an animation.
     * @param checked The new checked state.
     */
    public void setCheckedSilently(boolean checked) {
        setChecked(checked, true);
    }

    /**
     * Sets the checked state of the FAB.
     * @param checked The new checked state.
     * @param silently {@code true} to perform state changing silently, i.e., with no animations an without calling
     * {@link OnCheckedChangeListener}, {@code false} to perform state changing with an animation and calling listener
     * if not {@code null}.
     */
    private void setChecked(boolean checked, boolean silently) {

        // If trying to set the current state, ignore.
        if (checked == this.checked) {
            return;
        }
        this.checked = checked;

        if (silently) {

            // Now refresh the drawable state (so the icon changes).
            refreshDrawableState();

        } else {

            refreshDrawableStateWithAnimation();
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, checked);
            }
        }
    }

    private void refreshDrawableStateWithAnimation() {

        animate().setInterpolator(new OvershootInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        // Now refresh the drawable state (so the icon changes).
                        refreshDrawableState();
                        setRotation(-30);
                        animate()
                                .setDuration(180)
                                .rotation(0)
                                .scaleX(1)
                                .scaleY(1)
                                .alpha(1)
                                .start();
                    }
                })
                .setDuration(180)
                .rotation(30)
                .scaleX(0.85f)
                .scaleY(0.85f)
                .alpha(0.2f)
                .start();
    }

    /**
     * Register a callback to be invoked when the checked state of this button changes.
     * @param listener The callback to call on checked state change.
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onCheckedChangeListener = listener;
    }
}
