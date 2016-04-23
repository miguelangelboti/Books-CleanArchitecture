package com.miguelangelboti.books.domain.repository;

import java.util.List;

import javax.annotation.Nonnull;

import com.miguelangelboti.books.domain.entities.Book;

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
    void doBookSearch(BookSearchCallback callback, String query);

    /**
     * Callback definition to be invoked when search process has been completed.
     */
    interface BookSearchCallback {

        void onSuccess(@Nonnull List<Book> books);

        void onError();
    }

    /**
     * Performs a books search.
     * @param callback The callback to be invoked when search process has been completed.
     * @param id The book ID.
     */
    void getBook(GetBookCallback callback, String id);

    /**
     * Callback definition to be invoked when search process has been completed.
     */
    interface GetBookCallback {

        void onSuccess(@Nonnull Book book);

        void onError();
    }
}
