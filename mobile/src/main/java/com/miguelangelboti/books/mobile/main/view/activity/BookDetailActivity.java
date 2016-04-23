package com.miguelangelboti.books.mobile.main.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.miguelangelboti.books.mobile.di.components.BookDetailComponent;
import com.miguelangelboti.books.mobile.di.components.DaggerBookDetailComponent;
import com.miguelangelboti.books.mobile.di.modules.BookDetailModule;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.presenter.BookDetailPresenter;
import com.miguelangelboti.books.mobile.main.view.BookDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookDetailActivity extends BaseActivity implements HasComponent<BookDetailComponent>, BookDetailView {

    private static final String KEY_BOOK_ID = "KEY_BOOK_ID";

    // Workaround to get the book title, due to this bug:
    // https://code.google.com/p/android/issues/detail?id=77763
    private static final String KEY_BOOK_TITLE = "KEY_BOOK_TITLE";

    private BookDetailComponent bookDetailComponent;

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
    BookDetailPresenter bookDetailPresenter;

    /**
     * Get the {@link Intent} to launch this Activity.
     * @param context The context.
     * @param bookId The book ID.
     * @param title The text to set as window title.
     * @return The description of the activity to start.
     */
    public static Intent getCallingIntent(Context context, String bookId, String title) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(KEY_BOOK_ID, bookId);
        intent.putExtra(KEY_BOOK_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        setupWindowAnimations();
        setupToolbar();
        setupPresenter();
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

    private void setupToolbar() {

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        String title = (intent != null) ? intent.getStringExtra(KEY_BOOK_TITLE) : null;
        if (title != null) {
            setTitle(title);
        }
    }

    private void initializeInjector() {

        bookDetailComponent = DaggerBookDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .bookDetailModule(new BookDetailModule())
                .build();
        bookDetailComponent.inject(this);
    }

    private void setupPresenter() {

        Intent intent = getIntent();
        String bookId = (intent != null) ? intent.getStringExtra(KEY_BOOK_ID) : null;
        bookDetailPresenter.setView(this);
        bookDetailPresenter.setBookId(bookId);
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

    @Override
    public BookDetailComponent getComponent() {
        return bookDetailComponent;
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public void showProgress() {
        // TODO: To be completed...
    }

    @Override
    public void hideProgress() {
        // TODO: To be completed...
    }

    @Override
    public void loadBook(@Nonnull BookViewModel book) {

        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        String firstAuthor = ((authors != null) && (authors.size() > 0)) ? authors.get(0) : null;
        String description = book.getDescription();

        toolbar.setTitle(title);
        textView01.setText(title);
        textView02.setText(firstAuthor);
        textView03.setText(description);
        Picasso.with(this).load(book.getImageUrl()).into(imageView);
    }

    @Override
    public void showError() {
        // TODO: To be completed...
    }
}
