package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.favorites.GetFavoritesInteractor;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.model.mappers.BooksMapper;
import com.miguelangelboti.books.mobile.main.view.FavoritesView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

public class FavoritesPresenterImpl implements FavoritesPresenter, GetFavoritesInteractor.Callback {

    private FavoritesView view;

    private GetFavoritesInteractor getFavoritesInteractor;

    private BooksMapper booksMapper;

    @Inject
    public FavoritesPresenterImpl(GetFavoritesInteractor getFavoritesInteractor, BooksMapper booksMapper) {
        this.getFavoritesInteractor = getFavoritesInteractor;
        this.booksMapper = booksMapper;
    }

    @Override
    public void setView(@Nonnull FavoritesView view) {
        this.view = view;
    }

// region Presenter

    @Override
    public void resume() {
        view.showProgress();
        getFavoritesInteractor.execute(this);
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

// endregion
// region Interactor callbacks

    @Override
    public void onSuccess(@Nonnull List<Book> books) {
        List<BookViewModel> booksMapped = booksMapper.transform(books);
        view.loadFavorites(booksMapped);
        view.hideProgress();
    }

    @Override
    public void onError() {
        view.hideProgress();
        view.showError();
    }

// endregion
}
