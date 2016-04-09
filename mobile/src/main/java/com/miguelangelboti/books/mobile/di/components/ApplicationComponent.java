package com.miguelangelboti.books.mobile.di.components;

import android.content.Context;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.mobile.base.view.activity.BaseActivity;
import com.miguelangelboti.books.mobile.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Miguel Ángel Botija.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    BooksRepository booksRepository();
}
