package com.miguelangelboti.books.domain.interactor.favorites;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface definition for a interactor to get favorites books.
 * @author Miguel Ángel Botija.
 */
public interface GetFavoritesInteractor {

    void execute(Callback callback);

    /**
     * Callback definition to be invoked when a retrieve process has been completed.
     */
    interface Callback {

        void onSuccess(@Nonnull List<Book> books);

        void onError();
    }
}
