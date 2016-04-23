package com.miguelangelboti.books.domain.interactor.search;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.BaseInteractor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.BookSearchCallback;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class SearchInteractorImpl extends BaseInteractor implements SearchInteractor, BookSearchCallback {

    private final BooksRepository repository;

    private Callback callback;

    private String query;

    @Inject
    public SearchInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public void execute(final Callback callback, final String query) {

        this.callback = callback;
        this.query = query;
        execute();
    }

    @Override
    public void run() {

        if ((query == null) || (query.trim().length() == 0)) {
            post(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        } else {
            repository.doBookSearch(this, query);
        }
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
