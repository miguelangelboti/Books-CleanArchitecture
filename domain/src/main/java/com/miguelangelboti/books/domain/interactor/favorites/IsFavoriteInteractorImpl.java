package com.miguelangelboti.books.domain.interactor.favorites;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.BaseInteractor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetFavoritesCallback;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class IsFavoriteInteractorImpl extends BaseInteractor implements IsFavoriteInteractor, GetFavoritesCallback {

    private final BooksRepository repository;

    private Callback callback;

    private String bookId;

    @Inject
    public IsFavoriteInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public void execute(@Nonnull Callback callback, String bookId) {
        this.callback = callback;
        this.bookId = bookId;
        execute();
    }

    @Override
    public void run() {

        if ((bookId == null) || (bookId.length() == 0)) {
            post(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        } else {
            repository.getFavorites(this);
        }
    }

    @Override
    public void onSuccess(@Nonnull final List<Book> books) {

        post(new Runnable() {
            @Override
            public void run() {
                Book book = new Book(bookId);
                boolean isFavorite = books.contains(book);
                callback.onSuccess(isFavorite);
            }
        });
    }

    @Override
    public void onError() {
        post(new Runnable() {
            @Override
            public void run() {
                callback.onError();
            }
        });
    }
}
