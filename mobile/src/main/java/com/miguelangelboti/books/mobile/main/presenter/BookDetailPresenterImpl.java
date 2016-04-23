package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor.Callback;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.model.mappers.BooksMapper;
import com.miguelangelboti.books.mobile.main.view.BookDetailView;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * @author Miguel Ángel Botija.
 */
public class BookDetailPresenterImpl implements BookDetailPresenter {

    private final GetBookInteractor getBookInteractor;

    private final BooksMapper booksMapper;

    private BookDetailView view;

    @Inject
    public BookDetailPresenterImpl(@Nonnull GetBookInteractor getBookInteractor, @Nonnull BooksMapper booksMapper) {
        this.getBookInteractor = getBookInteractor;
        this.booksMapper = booksMapper;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void setView(@Nonnull BookDetailView view) {
        this.view = view;
    }

    @Override
    public void setBookId(String id) {

        getBookInteractor.execute(new Callback() {
            @Override
            public void onSuccess(@Nonnull Book book) {
                BookViewModel bookViewModel = booksMapper.transform(book);
                view.loadBook(bookViewModel);
            }

            @Override
            public void onError() {
                view.showError();
            }
        }, id);
    }
}
