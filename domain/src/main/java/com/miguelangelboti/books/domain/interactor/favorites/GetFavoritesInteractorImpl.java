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

public class GetFavoritesInteractorImpl extends BaseInteractor implements GetFavoritesInteractor, GetFavoritesCallback {

    private final BooksRepository repository;

    private Callback callback;

    @Inject
    public GetFavoritesInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public void execute(final Callback callback) {
        this.callback = callback;
        execute();
    }

    @Override
    public void run() {
        repository.getFavorites(this);
    }

    @Override
    public void onSuccess(@Nonnull final List<Book> books) {
        post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(books);
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
