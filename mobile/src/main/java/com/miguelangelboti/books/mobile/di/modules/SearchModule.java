package com.miguelangelboti.books.mobile.di.modules;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.search.SearchInteractor;
import com.miguelangelboti.books.domain.interactor.search.SearchInteractorImpl;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.main.presenter.SearchPresenter;
import com.miguelangelboti.books.mobile.main.presenter.SearchPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Miguel Ángel Botija.
 */
@Module
public class SearchModule {

    @Provides
    @PerActivity
    SearchInteractor provideSearchInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new SearchInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    SearchPresenter provideSearchPresenter(SearchPresenterImpl searchPresenter) {
        return searchPresenter;
    }
}
