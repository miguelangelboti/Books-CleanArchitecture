package com.miguelangelboti.books.mobile.di.components;

import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.di.modules.ActivityModule;
import com.miguelangelboti.books.mobile.di.modules.SearchModule;
import com.miguelangelboti.books.mobile.main.view.fragment.SearchFragment;

import dagger.Component;

/**
 * @author Miguel Ángel Botija.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, SearchModule.class})
public interface SearchComponent extends ActivityComponent {

    void inject(SearchFragment searchFragment);
}
