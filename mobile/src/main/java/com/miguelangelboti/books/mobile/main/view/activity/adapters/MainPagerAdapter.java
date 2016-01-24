package com.miguelangelboti.books.mobile.main.view.activity.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.miguelangelboti.books.mobile.main.view.fragment.SearchFragment;
import com.miguelangelboti.books.mobile.main.view.fragment.FavoritesFragment;

/**
 * @author Miguel Ángel Botija.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {

    public MainPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
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
        return "Tab " + position;
    }
}
