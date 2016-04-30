package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.mobile.base.presenter.Presenter;
import com.miguelangelboti.books.mobile.main.view.FavoritesView;

import javax.annotation.Nonnull;

/**
 * Interface definition for a favorites presenter.
 * @author Miguel Ángel Botija.
 */
public interface FavoritesPresenter extends Presenter {

    void setView(@Nonnull FavoritesView view);
}
