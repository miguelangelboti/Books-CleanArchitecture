package com.miguelangelboti.books.mobile.di.components;

import android.app.Activity;

import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.di.modules.ActivityModule;

import dagger.Component;

/**
 * @author Miguel Ángel Botija.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();
}
