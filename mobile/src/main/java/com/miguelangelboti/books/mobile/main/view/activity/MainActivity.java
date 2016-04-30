package com.miguelangelboti.books.mobile.main.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.activity.BaseActivity;
import com.miguelangelboti.books.mobile.di.HasComponent;
import com.miguelangelboti.books.mobile.di.components.BooksComponent;
import com.miguelangelboti.books.mobile.di.components.DaggerBooksComponent;
import com.miguelangelboti.books.mobile.di.modules.SearchModule;
import com.miguelangelboti.books.mobile.main.view.activity.adapters.MainPagerAdapter;

public class MainActivity extends BaseActivity implements HasComponent<BooksComponent> {

    private BooksComponent booksComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (viewPager != null) {
            viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    private void initializeInjector() {
        booksComponent = DaggerBooksComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchModule(new SearchModule())
                .build();
    }

    @Override
    public BooksComponent getComponent() {
        return booksComponent;
    }
}
