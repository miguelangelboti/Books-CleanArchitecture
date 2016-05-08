package com.miguelangelboti.books.domain.interactor.favorites;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface definition for a interactor to check if a book is a favorite.
 * @author Miguel Ángel Botija.
 */
public interface IsFavoriteInteractor {

    void execute(@Nonnull Callback callback, @Nullable String bookId);

    /**
     * Callback definition to be invoked when a retrieve process has been completed.
     */
    interface Callback {

        void onSuccess(boolean isFavorite);

        void onError();
    }
}
