package com.miguelangelboti.books.mobile.main.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.activity.BaseActivity;
import com.miguelangelboti.books.mobile.di.HasComponent;
import com.miguelangelboti.books.mobile.di.components.DaggerSearchComponent;
import com.miguelangelboti.books.mobile.di.components.SearchComponent;
import com.miguelangelboti.books.mobile.di.modules.SearchModule;
import com.miguelangelboti.books.mobile.main.view.activity.adapters.MainPagerAdapter;

public class MainActivity extends BaseActivity implements HasComponent<SearchComponent> {

    private SearchComponent searchComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initializeInjector() {
        searchComponent = DaggerSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchModule(new SearchModule())
                .build();
    }

    @Override
    public SearchComponent getComponent() {
        return searchComponent;
    }
}
