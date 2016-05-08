package com.miguelangelboti.books.mobile.di.modules;

import com.miguelangelboti.books.domain.executor.PostExecutionThread;
import com.miguelangelboti.books.domain.executor.ThreadExecutor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractor;
import com.miguelangelboti.books.domain.interactor.books.GetBookInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.AddFavoriteInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.DeleteFavoriteInteractorImpl;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoriteInteractor;
import com.miguelangelboti.books.domain.interactor.favorites.IsFavoriteInteractorImpl;
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
    IsFavoriteInteractor provideIsFavoriteInteractor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, BooksRepository booksRepository) {
        return new IsFavoriteInteractorImpl(threadExecutor, postExecutionThread, booksRepository);
    }

    @Provides
    @PerActivity
    AddFavoriteInteractor provideAddFavoriteInteractor(AddFavoriteInteractorImpl addFavoriteInteractor) {
        return addFavoriteInteractor;
    }

    @Provides
    @PerActivity
    DeleteFavoriteInteractor provideDeleteFavoriteInteractor(DeleteFavoriteInteractorImpl deleteFavoriteInteractor) {
        return deleteFavoriteInteractor;
    }

    @Provides
    @PerActivity
    BookDetailPresenter provideBookDetailPresenter(BookDetailPresenterImpl bookDetailPresenter) {
        return bookDetailPresenter;
    }
}
