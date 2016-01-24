package com.miguelangelboti.books.data.repository.datasources;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface BooksDataSource {

    interface Callback {

        void onSuccess(@Nonnull List<Book> books);

        void onError(Exception exception);
    }

    void getBooks(Callback callback, String query);
}
