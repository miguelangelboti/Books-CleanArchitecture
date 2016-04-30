package com.miguelangelboti.books.data.repository.datasources;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface BooksDataSource {

    void getBooks(@Nonnull GetBooksCallback callback, String query);

    interface GetBooksCallback {

        void onSuccess(@Nonnull List<Book> books);

        void onError(@Nonnull Exception exception);
    }

    void getBook(@Nonnull GetBookCallback callback, String bookId);

    interface GetBookCallback {

        void onSuccess(@Nonnull Book book);

        void onError(@Nonnull Exception exception);
    }

    void getFavorites(@Nonnull GetFavoritesCallback callback);

    interface GetFavoritesCallback {

        void onSuccess(@Nonnull List<Book> books);

        void onError(@Nonnull Exception exception);
    }

    void setFavorites(@Nonnull SetFavoritesCallback callback, List<Book> books);

    interface SetFavoritesCallback {

        void onSuccess();

        void onError(@Nonnull Exception exception);
    }
}
