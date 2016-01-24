package com.miguelangelboti.books.mobile.main.view.fragment;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.fragment.BaseFragment;

public class FavoritesFragment extends BaseFragment {

    /**
     * Instantiates a new favorites fragment.
     */
    public FavoritesFragment() {
        // Required empty public constructor.
    }

    /**
     * Use this factory method to create a new instance of this fragment.
     * @return A new instance of this fragment.
     */
    public static FavoritesFragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_favorites;
    }
}
