package com.miguelangelboti.books.domain.interactor.favorites;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.BaseInteractor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetBookCallback;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetFavoritesCallback;
import com.miguelangelboti.books.domain.repository.BooksRepository.SetFavoritesCallback;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class AddFavoriteInteractorImpl extends BaseInteractor implements AddFavoriteInteractor {

    private final BooksRepository repository;

    private Callback callback;

    private String bookId;

    @Inject
    public AddFavoriteInteractorImpl(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public void execute(@Nonnull Callback callback, @Nonnull String bookId) {
        this.callback = callback;
        this.bookId = bookId;
        execute();
    }

    @Override
    public void run() {

        repository.getBook(new GetBookCallback() {
            @Override
            public void onSuccess(@Nonnull final Book book) {
                addFavorite(book);
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

    private void addFavorite(@Nonnull final Book book) {

        repository.getFavorites(new GetFavoritesCallback() {
            @Override
            public void onSuccess(@Nonnull List<Book> books) {

                if (!books.contains(book)) {

                    books.add(book);
                    setFavorites(books);

                } else {

                    post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSuccess();
                        }
                    });
                }
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
        });
    }

    private void setFavorites(@Nonnull List<Book> books) {

        repository.setFavorites(new SetFavoritesCallback() {
            @Override
            public void onSuccess() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess();
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
        }, books);
    }
}
