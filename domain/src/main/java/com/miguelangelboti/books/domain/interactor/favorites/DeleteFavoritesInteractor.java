package com.miguelangelboti.books.domain.interactor.favorites;

import javax.annotation.Nonnull;

/**
 * Interface definition for a interactor to delete a book as favorite.
 * @author Miguel Ángel Botija.
 */
public interface DeleteFavoritesInteractor {

    /**
     * Executes this use case.
     * @param callback The callback to be invoked.
     * @param bookId The book ID.
     */
    void execute(@Nonnull Callback callback, @Nonnull String bookId);

    /**
     * Callback definition to be invoked when process has been completed.
     */
    interface Callback {

        void onSuccess();

        void onError();
    }
}
