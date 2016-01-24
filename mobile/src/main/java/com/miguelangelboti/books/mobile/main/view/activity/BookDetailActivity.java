package com.miguelangelboti.books.mobile.main.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {

    private static final String KEY_BOOK = "KEY_BOOK";

    @Bind(R.id.appBar)
    AppBarLayout appBar;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Bind(R.id.textView)
    TextView textView;

    private BookViewModel book;

    public static Intent getCallingIntent(Context context, BookViewModel book) {
        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(KEY_BOOK, book);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        Parcelable parcelable = getIntent().getParcelableExtra(KEY_BOOK);
        if (parcelable instanceof BookViewModel) {

            book = (BookViewModel) parcelable;
            setTitle(book.getTitle());
            textView.setText(book.getDescription());

//            Picasso.with(this).load(book.getImageUrl()).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    Palette palette = Palette.from(bitmap).generate();
//                    Palette.Swatch swatch = palette.getLightVibrantSwatch();
//                    if (swatch != null) {
//                        appBar.setBackgroundColor(swatch.getRgb());
//                    }
//                }
//
//                @Override
//                public void onBitmapFailed(Drawable errorDrawable) {
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//                }
//            });
        }
    }
}
