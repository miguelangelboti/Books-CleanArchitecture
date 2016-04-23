package com.miguelangelboti.books.data.repository.datasources;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface BooksDataSource {

    interface GetBooksCallback {

        void onSuccess(@Nonnull List<Book> books);

        void onError(@Nonnull Exception exception);
    }

    void getBooks(@Nonnull GetBooksCallback callback, String query);

    interface GetBookCallback {

        void onSuccess(@Nonnull Book book);

        void onError(@Nonnull Exception exception);
    }

    void getBook(@Nonnull GetBookCallback callback, String bookId);
}
