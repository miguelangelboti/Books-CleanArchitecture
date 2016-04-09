package com.miguelangelboti.books.mobile.di.modules;

import android.content.Context;

import com.miguelangelboti.books.data.executor.JobExecutor;
import com.miguelangelboti.books.data.repository.BooksRepositoryImpl;
import com.miguelangelboti.books.data.repository.datasources.BooksDataSource;
import com.miguelangelboti.books.data.repository.datasources.BooksLocalDataSource;
import com.miguelangelboti.books.data.repository.datasources.BooksNetworkDataSource;
import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.mobile.BooksApplication;
import com.miguelangelboti.books.mobile.executor.UiThread;
import com.miguelangelboti.books.mobile.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Miguel Ángel Botija.
 */
@Module
public class ApplicationModule {

    private final BooksApplication application;

    public ApplicationModule(BooksApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UiThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    BooksRepository provideBooksRepository(BooksRepositoryImpl booksRepository) {
        return booksRepository;
    }

    @Provides
    BooksDataSource provideLocalDataSource(BooksLocalDataSource booksLocalDataSource) {
        return booksLocalDataSource;
    }

    @Provides
    BooksDataSource provideNetworkDataSource(BooksNetworkDataSource booksNetworkDataSource) {
        return booksNetworkDataSource;
    }
}
