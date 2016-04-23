package com.miguelangelboti.books.mobile.di.components;

import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.di.modules.ActivityModule;
import com.miguelangelboti.books.mobile.di.modules.BookDetailModule;
import com.miguelangelboti.books.mobile.main.view.activity.BookDetailActivity;

import dagger.Component;

/**
 * @author Miguel Ángel Botija.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BookDetailModule.class})
public interface BookDetailComponent extends ActivityComponent {

    void inject(BookDetailActivity bookDetailActivity);
}
