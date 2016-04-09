package com.miguelangelboti.books.mobile;

import android.app.Application;

import com.miguelangelboti.books.mobile.di.components.ApplicationComponent;
import com.miguelangelboti.books.mobile.di.components.DaggerApplicationComponent;
import com.miguelangelboti.books.mobile.di.modules.ApplicationModule;

/**
 * @author Miguel Ángel Botija.
 */
public class BooksApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
