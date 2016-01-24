package com.miguelangelboti.books.data.repository;

import com.miguelangelboti.books.data.repository.datasources.BooksDataSource;
import com.miguelangelboti.books.data.repository.datasources.BooksLocalDataStore;
import com.miguelangelboti.books.data.repository.datasources.BooksNetworkDataSource;
import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.repository.BooksRepository;

import java.util.List;

import javax.annotation.Nonnull;

/**
 * @author Miguel Ángel Botija.
 */
public class BooksRepositoryImpl implements BooksRepository {

    private static BooksRepositoryImpl INSTANCE;

    private final BooksLocalDataStore localDataStore;

    private final BooksNetworkDataSource networkDataSource;

    public BooksRepositoryImpl(BooksLocalDataStore localDataStore, BooksNetworkDataSource networkDataSource) {
        this.localDataStore = localDataStore;
        this.networkDataSource = networkDataSource;
    }

    public static synchronized BooksRepositoryImpl getInstance(BooksLocalDataStore localDataStore, BooksNetworkDataSource networkDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new BooksRepositoryImpl(localDataStore, networkDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getBooks(final Callback callback, String query) {

        networkDataSource.getBooks(new BooksDataSource.Callback() {
            @Override
            public void onSuccess(@Nonnull List<Book> books) {
                callback.onSuccess(books);
            }

            @Override
            public void onError(Exception exception) {
                callback.onError();
            }
        }, query);
    }
}
