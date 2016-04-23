package com.miguelangelboti.books.domain.interactor.books;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.BaseInteractor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetBookCallback;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * @author Miguel Ángel Botija.
 */
public class GetBookInteractorImpl extends BaseInteractor implements GetBookInteractor, GetBookCallback {

    private final BooksRepository repository;

    private Callback callback;

    private String bookId;

    @Inject
    public GetBookInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
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

        if ((bookId == null) || (bookId.trim().length() == 0)) {
            post(new Runnable() {
                @Override
                public void run() {
                    callback.onError();
                }
            });
        } else {
            repository.getBook(this, bookId);
        }
    }

    @Override
    public void onSuccess(@Nonnull final Book book) {
        post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(book);
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
