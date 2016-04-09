package com.miguelangelboti.books.mobile.main.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.base.view.activity.BaseActivity;
import com.miguelangelboti.books.mobile.di.HasComponent;
import com.miguelangelboti.books.mobile.di.components.DaggerSearchComponent;
import com.miguelangelboti.books.mobile.di.components.SearchComponent;
import com.miguelangelboti.books.mobile.di.modules.SearchModule;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.presenter.SearchPresenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements HasComponent<SearchComponent> {

    private static final String KEY_BOOK = "KEY_BOOK";

    private SearchComponent searchComponent;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.imageView)
    ImageView imageView;

    @Bind(R.id.textView01)
    TextView textView01;

    @Bind(R.id.textView02)
    TextView textView02;

    @Bind(R.id.textView03)
    TextView textView03;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Inject
    SearchPresenter searchPresenter;

    public static Intent getCallingIntent(Context context, BookViewModel book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(KEY_BOOK, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setupWindowAnimations();
        bindData();
    }

    private void initializeInjector() {

        searchComponent = DaggerSearchComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchModule(new SearchModule())
                .build();
        searchComponent.inject(this);
    }

    @Override
    public SearchComponent getComponent() {
        return searchComponent;
    }

    private void bindData() {

        Parcelable parcelable = getIntent().getParcelableExtra(KEY_BOOK);
        if (parcelable instanceof BookViewModel) {

            BookViewModel book = (BookViewModel) parcelable;
            String title = book.getTitle();
            List<String> authors = book.getAuthors();
            String firstAuthor = ((authors != null) && (authors.size() > 0)) ? authors.get(0) : null;
            String description = book.getDescription();

            setTitle(title);
            textView01.setText(title);
            textView02.setText(firstAuthor);
            textView03.setText(description);
            Picasso.with(this).load(book.getImageUrl()).into(imageView);
        }
    }

    private void setupWindowAnimations() {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setAllowEnterTransitionOverlap(true);
            Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.detail_enter_trasition);
            Transition exitTransition = TransitionInflater.from(this).inflateTransition(R.transition.detail_exit_trasition);
            window.setEnterTransition(enterTransition);
            window.setReturnTransition(exitTransition);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finishWithAnimation();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
