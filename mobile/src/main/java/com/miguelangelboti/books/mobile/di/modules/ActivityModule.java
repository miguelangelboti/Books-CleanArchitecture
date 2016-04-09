package com.miguelangelboti.books.mobile.di.modules;

import android.app.Activity;

import com.miguelangelboti.books.mobile.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Miguel Ángel Botija.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
