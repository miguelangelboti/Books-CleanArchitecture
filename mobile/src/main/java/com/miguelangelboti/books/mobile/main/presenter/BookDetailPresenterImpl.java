package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor.Callback;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoritesInteractor;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;
import com.miguelangelboti.books.mobile.main.model.mappers.BooksMapper;
import com.miguelangelboti.books.mobile.main.view.BookDetailView;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * @author Miguel Ángel Botija.
 */
public class BookDetailPresenterImpl implements BookDetailPresenter {

    private final BooksMapper booksMapper;

    private final GetBookInteractor getBookInteractor;

    private IsFavoritesInteractor isFavoritesInteractor;

    private AddFavoritesInteractor addFavoritesInteractor;

    private DeleteFavoritesInteractor deleteFavoritesInteractor;

    private BookDetailView view;

    @Inject
    public BookDetailPresenterImpl(@Nonnull BooksMapper booksMapper, @Nonnull GetBookInteractor getBookInteractor, @Nonnull IsFavoritesInteractor isFavoritesInteractor, @Nonnull AddFavoritesInteractor addFavoritesInteractor, @Nonnull DeleteFavoritesInteractor deleteFavoritesInteractor) {
        this.booksMapper = booksMapper;
        this.getBookInteractor = getBookInteractor;
        this.isFavoritesInteractor = isFavoritesInteractor;
        this.addFavoritesInteractor = addFavoritesInteractor;
        this.deleteFavoritesInteractor = deleteFavoritesInteractor;
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
    public void setBookId(final String bookId) {

        getBookInteractor.execute(new Callback() {
            @Override
            public void onSuccess(@Nonnull Book book) {
                BookViewModel bookViewModel = booksMapper.transform(book);
                view.loadBook(bookViewModel);
                checkFavorite(bookId);
            }

            @Override
            public void onError() {
                view.showError();
            }
        }, bookId);
    }

    private void checkFavorite(String bookId) {

        isFavoritesInteractor.execute(new IsFavoritesInteractor.Callback() {
            @Override
            public void onSuccess(boolean isFavorite) {

                if (isFavorite) {
                    view.checkFavorite();
                } else {
                    view.uncheckFavorite();
                }
            }

            @Override
            public void onError() {
            }
        }, bookId);
    }

    @Override
    public void onFavoriteButtonClick(@Nonnull final String bookId, boolean isChecked) {

        if (isChecked) {
            addFavorite(bookId);
        } else {
            deleteFavorite(bookId);
        }
    }

    private void addFavorite(@Nonnull String bookId) {

        addFavoritesInteractor.execute(new AddFavoritesInteractor.Callback() {
            @Override
            public void onSuccess() {
                view.checkFavorite();
            }

            @Override
            public void onError() {
                // Uncheck the favorite again.
                view.uncheckFavorite();
            }
        }, bookId);
    }

    private void deleteFavorite(@Nonnull String bookId) {

        deleteFavoritesInteractor.execute(new DeleteFavoritesInteractor.Callback() {
            @Override
            public void onSuccess() {
                view.uncheckFavorite();
            }

            @Override
            public void onError() {
                // Check the favorite again.
                view.checkFavorite();
            }
        }, bookId);
    }
}
