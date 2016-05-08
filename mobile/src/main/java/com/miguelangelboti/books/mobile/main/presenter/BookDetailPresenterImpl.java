package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor.Callback;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoriteInteractor;
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

    private IsFavoriteInteractor isFavoriteInteractor;

    private AddFavoriteInteractor addFavoriteInteractor;

    private DeleteFavoriteInteractor deleteFavoriteInteractor;

    private BookDetailView view;

    @Inject
    public BookDetailPresenterImpl(@Nonnull BooksMapper booksMapper, @Nonnull GetBookInteractor getBookInteractor, @Nonnull IsFavoriteInteractor isFavoriteInteractor, @Nonnull AddFavoriteInteractor addFavoriteInteractor, @Nonnull DeleteFavoriteInteractor deleteFavoriteInteractor) {
        this.booksMapper = booksMapper;
        this.getBookInteractor = getBookInteractor;
        this.isFavoriteInteractor = isFavoriteInteractor;
        this.addFavoriteInteractor = addFavoriteInteractor;
        this.deleteFavoriteInteractor = deleteFavoriteInteractor;
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

        isFavoriteInteractor.execute(new IsFavoriteInteractor.Callback() {
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

        addFavoriteInteractor.execute(new AddFavoriteInteractor.Callback() {
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

        deleteFavoriteInteractor.execute(new DeleteFavoriteInteractor.Callback() {
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
