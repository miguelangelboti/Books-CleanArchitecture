package com.miguelangelboti.books.mobile.main.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.main.view.activity.adapters.MainPagerAdapter;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @Bind(R.id.appBar)
//    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            appBar.setOnTouchListener(this);
//        }
    }

//    @Override
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//            int startX = (int) motionEvent.getX();
//            int startY = (int) motionEvent.getY();
//            Animator circularReveal = ViewAnimationUtils.createCircularReveal(appBar, startX, startY, 0, view.getHeight());
//            appBar.setBackgroundColor(getResources().getColor(R.color.accent));
//            circularReveal.start();
//        }
//        return false;
//    }
}
