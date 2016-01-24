package com.miguelangelboti.books.mobile.navigation;

import android.content.Context;
import android.content.Intent;

import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.view.activity.BookDetailActivity;
import com.miguelangelboti.books.mobile.main.view.activity.MainActivity;

/**
 * Class used to navigate through the application.
 */
public class Navigator {

    public void Navigator() {
    }

    /**
     * Goes to the book details screen.
     * @param context A context needed to open the destiny activity.
     */
    public void navigateToBooksDetails(Context context, BookViewModel book) {
        if (context != null) {
            Intent intent = BookDetailActivity.getCallingIntent(context, book);
            context.startActivity(intent);
        }
    }
}
