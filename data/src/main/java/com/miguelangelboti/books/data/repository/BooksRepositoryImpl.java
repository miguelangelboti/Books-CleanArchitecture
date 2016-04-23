package com.miguelangelboti.books.data.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.miguelangelboti.books.data.repository.datasources.BooksDataSource;
import com.miguelangelboti.books.data.repository.datasources.BooksDataSource.GetBooksCallback;
import com.miguelangelboti.books.data.repository.datasources.BooksLocalDataSource;
import com.miguelangelboti.books.data.repository.datasources.BooksNetworkDataSource;
import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.repository.BooksRepository;

/**
 * @author Miguel Ángel Botija.
 */
@Singleton
public class BooksRepositoryImpl implements BooksRepository {

    private final BooksLocalDataSource localDataSource;

    private final BooksNetworkDataSource networkDataSource;

    @Inject
    public BooksRepositoryImpl(BooksLocalDataSource localDataSource, BooksNetworkDataSource networkDataSource) {
        this.localDataSource = localDataSource;
        this.networkDataSource = networkDataSource;
    }

    @Override
    public void doBookSearch(final BookSearchCallback callback, String query) {

        networkDataSource.getBooks(new GetBooksCallback() {
            @Override
            public void onSuccess(@Nonnull List<Book> books) {
                localDataSource.setBooks(books);
                callback.onSuccess(books);
            }

            @Override
            public void onError(@Nonnull Exception exception) {
                callback.onError();
            }
        }, query);
    }

    @Override
    public void getBook(final GetBookCallback callback, final String id) {

        localDataSource.getBook(new BooksDataSource.GetBookCallback() {
            @Override
            public void onSuccess(@Nonnull Book book) {
                callback.onSuccess(book);
            }

            @Override
            public void onError(@Nonnull Exception exception) {
                getBookFromNetwork(callback, id);
            }
        }, id);
    }

    private void getBookFromNetwork(final GetBookCallback callback, String id) {

        networkDataSource.getBook(new BooksDataSource.GetBookCallback() {
            @Override
            public void onSuccess(@Nonnull Book book) {
                callback.onSuccess(book);
            }

            @Override
            public void onError(@Nonnull Exception exception) {
                callback.onError();
            }
        }, id);
    }
}
