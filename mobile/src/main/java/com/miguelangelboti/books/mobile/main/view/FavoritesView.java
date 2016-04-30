package com.miguelangelboti.books.mobile.main.view;

import com.miguelangelboti.books.mobile.base.view.View;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface definition for a favorites view.
 * @author Miguel Ángel Botija.
 */
public interface FavoritesView extends View {

    void loadFavorites(@Nonnull List<BookViewModel> books);

    void showError();
}
