package com.miguelangelboti.books.domain.interactor.search;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface definition for a interactor to search books.
 * @author Miguel Ángel Botija.
 */
public interface SearchInteractor {

    /**
     * Executes this use case.
     * @param callback The callback to be invoked.
     * @param query The query to perform the search.
     */
    void execute(@Nonnull Callback callback, String query);

    /**
     * Callback definition to be invoked when search process has been completed.
     */
    interface Callback {

        void onSuccess(@Nonnull List<Book> books);

        void onError();
    }
}
