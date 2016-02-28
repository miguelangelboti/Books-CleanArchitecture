package com.miguelangelboti.books.mobile.navigation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.miguelangelboti.books.R;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.view.activity.BookDetailActivity;

/**
 * Class used to navigate through the application.
 */
public class Navigator {

    /**
     * Goes to the book details screen.
     * @param context A context needed to open the destiny activity.
     */
    public void navigateToBooksDetails(Context context, BookViewModel book, View imageView, View textView01, View textView02) {

        if (context != null) {
            Intent intent = BookDetailActivity.getCallingIntent(context, book);
            Bundle bundle = null;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                Activity activity = getActivity(context);
                if (activity != null) {

                    Resources resources = context.getResources();
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,
                            Pair.create(imageView, resources.getString(R.string.transition_name_book_image)),
                            Pair.create(textView01, resources.getString(R.string.transition_name_book_title)),
                            Pair.create(textView02, resources.getString(R.string.transition_name_book_authors)));
                    bundle = options.toBundle();
                }
            }

            context.startActivity(intent, bundle);
        }
    }

    public void openWeb(Context context, String url) {
        if (url != null) {
            Uri uri = Uri.parse(url);
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(browserIntent);
        }
    }

    private Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
