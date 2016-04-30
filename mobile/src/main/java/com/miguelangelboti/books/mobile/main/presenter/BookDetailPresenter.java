package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.mobile.base.presenter.Presenter;
import com.miguelangelboti.books.mobile.main.view.BookDetailView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Interface definition for a books search presenter.
 * @author Miguel Ángel Botija.
 */
public interface BookDetailPresenter extends Presenter {

    void setView(@Nonnull BookDetailView view);

    void setBookId(@Nullable String bookId);

    void onFavoriteButtonClick(@Nonnull String bookId, boolean isChecked);
}
