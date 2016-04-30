package com.miguelangelboti.books.mobile.di.components;

import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.di.modules.ActivityModule;
import com.miguelangelboti.books.mobile.di.modules.FavoritesModule;
import com.miguelangelboti.books.mobile.di.modules.SearchModule;
import com.miguelangelboti.books.mobile.main.view.fragment.FavoritesFragment;
import com.miguelangelboti.books.mobile.main.view.fragment.SearchFragment;

import dagger.Component;

/**
 * @author Miguel Ángel Botija.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, SearchModule.class, FavoritesModule.class})
public interface BooksComponent extends ActivityComponent {

    void inject(SearchFragment searchFragment);

    void inject(FavoritesFragment searchFragment);
}
