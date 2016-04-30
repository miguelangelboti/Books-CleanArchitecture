package com.miguelangelboti.books.mobile.di.modules;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoritesInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoritesInteractorImpl;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.mobile.di.PerActivity;
import com.miguelangelboti.books.mobile.main.presenter.BookDetailPresenter;
import com.miguelangelboti.books.mobile.main.presenter.BookDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Miguel Ángel Botija.
 */
@Module
public class BookDetailModule {

    @Provides
    @PerActivity
    GetBookInteractor provideGetBookInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new GetBookInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    IsFavoritesInteractor provideIsFavoritesInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new IsFavoritesInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    AddFavoritesInteractor provideAddFavoritesInteractor(AddFavoritesInteractorImpl addFavoritesInteractor) {
        return addFavoritesInteractor;
    }

    @Provides
    @PerActivity
    DeleteFavoritesInteractor provideDeleteFavoritesInteractor(DeleteFavoritesInteractorImpl deleteFavoritesInteractor) {
        return deleteFavoritesInteractor;
    }

    @Provides
    @PerActivity
    BookDetailPresenter provideBookDetailPresenter(BookDetailPresenterImpl bookDetailPresenter) {
        return bookDetailPresenter;
    }
}
