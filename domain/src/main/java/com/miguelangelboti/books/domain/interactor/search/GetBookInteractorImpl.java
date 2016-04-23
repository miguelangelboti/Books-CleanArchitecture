package com.miguelangelboti.books.domain.interactor.search;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.BaseInteractor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetBookCallback;

/**
 * @author Miguel Ángel Botija.
 */
public class GetBookInteractorImpl extends BaseInteractor implements GetBookInteractor {

    private final BooksRepository repository;

    private Callback callback;

    private String bookId;

    @Inject
    public GetBookInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public void execute(Callback callback, String bookId) {
        this.callback = callback;
        this.bookId = bookId;
        execute();
    }

    @Override
    public void run() {

        repository.getBook(new GetBookCallback() {
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
        }, bookId);
    }
}
