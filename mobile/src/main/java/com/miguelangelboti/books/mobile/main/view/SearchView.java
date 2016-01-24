package com.miguelangelboti.books.mobile.main.view;

import com.miguelangelboti.books.mobile.base.view.View;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface definition for a books search view.
 * @author Miguel Ángel Botija.
 */
public interface SearchView extends View {

    void loadBooks(@Nonnull List<BookViewModel> books);

    void showError();
}
