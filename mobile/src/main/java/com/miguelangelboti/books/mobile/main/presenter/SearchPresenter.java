package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.mobile.base.presenter.Presenter;

/**
 * Interface definition for a books search presenter.
 * @author Miguel Ángel Botija.
 */
public interface SearchPresenter extends Presenter {

    void doSearch(String query);
}
