package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.search.SearchInteractor;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.model.mappers.BooksMapper;
import com.miguelangelboti.books.mobile.main.view.SearchView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class SearchPresenterImpl implements SearchPresenter, SearchInteractor.Callback {

    private SearchView view;

    private SearchInteractor searchInteractor;

    private BooksMapper booksMapper;

    private List<BookViewModel> booksMapped;

    @Inject
    public SearchPresenterImpl(SearchInteractor searchInteractor, BooksMapper booksMapper) {
        this.searchInteractor = searchInteractor;
        this.booksMapper = booksMapper;
    }

    @Override
    public void setView(@Nonnull SearchView view) {
        this.view = view;
    }

// region Presenter

    @Override
    public void resume() {
        if (booksMapped != null) {
            view.loadBooks(booksMapped);
        } else {
            view.showProgress();
            searchInteractor.execute(this, "Android");
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doSearch(String query) {
        view.showProgress();
        searchInteractor.execute(this, query);
    }

// endregion
// region Interactor callbacks

    @Override
    public void onSuccess(@Nonnull List<Book> books) {
        booksMapped = booksMapper.transform(books);
        view.hideProgress();
        view.loadBooks(booksMapped);
    }

    @Override
    public void onError() {
        view.hideProgress();
        view.showError();
    }

// endregion
}
