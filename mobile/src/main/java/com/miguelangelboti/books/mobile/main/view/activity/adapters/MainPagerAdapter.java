package com.miguelangelboti.books.mobile.main.view.activity.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.main.view.fragment.FavoritesFragment;
import com.miguelangelboti.books.mobile.main.view.fragment.SearchFragment;

/**
 * @author Miguel Ángel Botija.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private final Context context;

    public MainPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SearchFragment.newInstance();
        } else {
            return FavoritesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.search);
        } else {
            return context.getString(R.string.favorites);
        }
    }
}
