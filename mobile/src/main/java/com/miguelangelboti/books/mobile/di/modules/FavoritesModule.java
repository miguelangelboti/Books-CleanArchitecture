package com.miguelangelboti.books.mobile.di.modules;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoriteInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.GetFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.GetFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoriteInteractorImpl;
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
    IsFavoriteInteractor provideIsFavoriteInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new IsFavoriteInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    AddFavoriteInteractor provideAddFavoriteInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new AddFavoriteInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
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
