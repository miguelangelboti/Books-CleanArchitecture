package com.miguelangelboti.books.mobile.main.presenter;


import android.support.annotation.NonNull;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.books.SearchInteractor;
import com.miguelangelboti.books.mobile.main.view.SearchView;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.model.mappers.BooksMapper;

import java.util.List;

public class SearchPresenterImpl implements SearchPresenter, SearchInteractor.Callback {

    private SearchView searchView;

    private SearchInteractor searchInteractor;

    private BooksMapper booksMapper;

    private List<BookViewModel> booksMapped;

    public SearchPresenterImpl(SearchView searchView, SearchInteractor searchInteractor, BooksMapper booksMapper) {
        this.searchView = searchView;
        this.searchInteractor = searchInteractor;
        this.booksMapper = booksMapper;
    }

// region Presenter

    @Override
    public void resume() {
        if (booksMapped != null) {
            searchView.loadBooks(booksMapped);
        } else {
            searchView.showProgress();
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
        searchView.showProgress();
        searchInteractor.execute(this, query);
    }

// endregion
// region Interactor callbacks

    @Override
    public void onSuccess(@NonNull List<Book> books) {
        booksMapped = booksMapper.transform(books);
        searchView.hideProgress();
        searchView.loadBooks(booksMapped);
    }

    @Override
    public void onError() {
        searchView.hideProgress();
        searchView.showError();
    }

// endregion
}
