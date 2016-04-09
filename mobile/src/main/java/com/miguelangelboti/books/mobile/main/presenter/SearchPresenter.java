package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.mobile.base.presenter.Presenter;
import com.miguelangelboti.books.mobile.main.view.SearchView;

import javax.annotation.Nonnull;

/**
 * Interface definition for a books search presenter.
 * @author Miguel Ángel Botija.
 */
public interface SearchPresenter extends Presenter {

    void setView(@Nonnull SearchView view);

    void doSearch(String query);
}
