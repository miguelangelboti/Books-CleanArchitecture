package com.miguelangelboti.books.mobile.main.presenter;

import com.miguelangelboti.books.mobile.base.presenter.Presenter;
import com.miguelangelboti.books.mobile.main.view.BookDetailView;

import javax.annotation.Nonnull;

/**
 * Interface definition for a books search presenter.
 * @author Miguel Ángel Botija.
 */
public interface BookDetailPresenter extends Presenter {

    void setView(@Nonnull BookDetailView view);

    void setBookId(String id);
}
