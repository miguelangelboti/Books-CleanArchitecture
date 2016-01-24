package com.miguelangelboti.books.domain.repository;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface definition for a books repository.
 * @author Miguel Ángel Botija.
 */
public interface BooksRepository {

    /**
     * Performs a books search.
     * @param callback The callback to be invoked when search process has been completed.
     * @param query The query to search.
     */
    void getBooks(Callback callback, String query);

    /**
     * Callback definition to be invoked when search process has been completed.
     */
    interface Callback {

        void onSuccess(@Nonnull List<Book> books);

        void onError();
    }
}
