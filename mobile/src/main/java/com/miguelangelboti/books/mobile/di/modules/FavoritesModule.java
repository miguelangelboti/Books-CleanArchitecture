package com.miguelangelboti.books.mobile.di.modules;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.GetFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.GetFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.main.presenter.BookDetailPresenter;
import com.miguelangelboti.books.mobile.main.presenter.BookDetailPresenterImpl;
import com.miguelangelboti.books.mobile.main.presenter.FavoritesPresenter;
import com.miguelangelboti.books.mobile.main.presenter.FavoritesPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Miguel Ángel Botija.
 */
@Module
public class FavoritesModule {

    @Provides
    @PerActivity
    GetFavoritesInteractor provideGetFavoritesInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new GetFavoritesInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    IsFavoritesInteractor provideIsFavoritesInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new IsFavoritesInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    AddFavoritesInteractor provideSetFavoritesInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new AddFavoritesInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    BookDetailPresenter provideBookDetailPresenter(BookDetailPresenterImpl bookDetailPresenter) {
        return bookDetailPresenter;
    }

    @Provides
    @PerActivity
    FavoritesPresenter provideFavoritesPresenter(FavoritesPresenterImpl favoritesPresenter) {
        return favoritesPresenter;
    }
}
