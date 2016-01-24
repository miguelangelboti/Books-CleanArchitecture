package com.miguelangelboti.books.mobile.base.view.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelangelboti.books.mobile.navigation.Navigator;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements com.miguelangelboti.books.mobile.base.view.View {

    protected Navigator navigator = new Navigator();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Gets the layout ID to inflate fragment view.
     * @return The layout ID of this fragment.
     */
    protected abstract int getLayoutId();

    @Override
    public void showProgress() {
        showSnackMessage("showProgress");
    }

    @Override
    public void hideProgress() {
        showSnackMessage("hideProgress");
    }

    /**
     * Shows a message as SnackBar.
     * @param message An string representing a message to be shown.
     */
    protected void showSnackMessage(String message) {
        View view = getView();
        if (view != null) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        }
    }
}
