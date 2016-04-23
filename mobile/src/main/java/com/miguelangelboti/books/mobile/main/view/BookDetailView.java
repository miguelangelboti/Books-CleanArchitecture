package com.miguelangelboti.books.mobile.main.view;

import com.miguelangelboti.books.mobile.base.view.View;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import javax.annotation.Nonnull;

/**
 * Interface definition for a book detail view.
 * @author Miguel Ángel Botija.
 */
public interface BookDetailView extends View {

    void loadBook(@Nonnull BookViewModel book);

    void showError();
}
