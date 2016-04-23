package com.miguelangelboti.books.domain.interactor.search;

import javax.annotation.Nonnull;

import com.miguelangelboti.books.domain.entities.Book;

/**
 * Interface definition for a interactor to get a book.
 * @author Miguel Ángel Botija.
 */
public interface GetBookInteractor {

    /**
     * Executes this use case.
     * @param callback The callback to be invoked.
     * @param bookId The book ID.
     */
    void execute(Callback callback, String bookId);

    /**
     * Callback definition to be invoked when a search process has been completed.
     */
    interface Callback {

        void onSuccess(@Nonnull Book book);

        void onError();
    }
}
