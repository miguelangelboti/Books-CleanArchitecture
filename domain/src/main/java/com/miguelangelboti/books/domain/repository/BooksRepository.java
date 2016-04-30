package com.miguelangelboti.books.domain.repository;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface definition for a books repository.
 * @author Miguel Ángel Botija.
 */
public interface BooksRepository {

    /**
     * Performs a books search.
     * @param callback The callback to be invoked.
     * @param query The query to search.
     */
    void doBookSearch(@Nonnull BookSearchCallback callback, String query);

    /**
     * Callback definition to be invoked when search process has been completed.
     */
    interface BookSearchCallback {

        void onSuccess(@Nonnull List<Book> books);

        void onError();
    }

    /**
     * Get a book by its ID.
     * @param callback The callback to be invoked.
     * @param id The book ID.
     */
    void getBook(GetBookCallback callback, String id);

    /**
     * Callback definition to be invoked when process has been completed.
     */
    interface GetBookCallback {

        void onSuccess(@Nonnull Book book);

        void onError();
    }

    /**
     * Gets the favorites books.
     * @param callback The callback to be invoked when retrieve process has been completed.
     */
    void getFavorites(@Nonnull GetFavoritesCallback callback);

    /**
     * Callback definition to be invoked when retrieve process has been completed.
     */
    interface GetFavoritesCallback {

        void onSuccess(@Nonnull final List<Book> books);

        void onError();
    }

    /**
     * Sets the favorites books.
     * @param callback The callback to be invoked when retrieve process has been completed.
     * @param books The list of favorites.
     */
    void setFavorites(@Nonnull SetFavoritesCallback callback, @Nullable List<Book> books);

    /**
     * Callback definition to be invoked when retrieve process has been completed.
     */
    interface SetFavoritesCallback {

        void onSuccess();

        void onError();
    }
}
