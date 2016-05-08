package com.miguelangelboti.books.domain.interactor.favorites;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.ThreadExecutorMock;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetBookCallback;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetFavoritesCallback;
import com.miguelangelboti.books.domain.repository.BooksRepository.SetFavoritesCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Miguel Ãngel Botija.
 */
public class AddFavoriteInteractorImplTest {

    private AddFavoriteInteractor interactorUnderTest;

    private AddFavoriteInteractor.Callback mockCallback;

    private BooksRepository mockRepository;

    @Before
    public void setup() {

        ThreadExecutorMock executor = new ThreadExecutorMock();
        mockCallback = mock(AddFavoriteInteractor.Callback.class);
        mockRepository = mock(BooksRepository.class);
        interactorUnderTest = new AddFavoriteInteractorImpl(executor, executor, mockRepository);
    }

    @Test
    public void whenBookIdIsEmpty_RequestFails() {

        interactorUnderTest.execute(mockCallback, "");
        verify(mockRepository, never()).getBook(any(GetBookCallback.class), anyString());
        verify(mockRepository, never()).getFavorites(any(GetFavoritesCallback.class));
        verify(mockRepository, never()).setFavorites(any(SetFavoritesCallback.class), anyListOf(Book.class));
        verify(mockCallback, never()).onSuccess();
        verify(mockCallback).onError();
    }

    @Test
    public void whenBookIdIsNotEmptyAndIsNotFavorite_RequestSuccess() {

        List<Book> favorites = new ArrayList<>();
        List<Book> favoritesToSet = new ArrayList<>();
        Book book = new Book("1");
        favoritesToSet.add(book);

        setupGetBook(book);
        setupGetFavorites(favorites);
        setupSetFavorites();

        interactorUnderTest.execute(mockCallback, "1");
        verify(mockCallback, never()).onError();
        verify(mockRepository).setFavorites(any(SetFavoritesCallback.class), eq(favoritesToSet));
        verify(mockCallback).onSuccess();
    }

    @Test
    public void whenBookIdIsNotEmptyAndIsFavorite_RequestSuccess() {

        List<Book> favorites = new ArrayList<>();
        Book book = new Book("1");
        favorites.add(book);

        setupGetBook(book);
        setupGetFavorites(favorites);

        interactorUnderTest.execute(mockCallback, "1");
        verify(mockCallback, never()).onError();
        verify(mockRepository, never()).setFavorites(any(SetFavoritesCallback.class), anyListOf(Book.class));
        verify(mockCallback).onSuccess();
    }

    private void setupGetBook(final Book book) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GetBookCallback callback = (GetBookCallback) invocation.getArguments()[0];
                callback.onSuccess(book);
                return null;
            }
        }).when(mockRepository).getBook(any(GetBookCallback.class), anyString());
    }

    private void setupGetFavorites(final List<Book> favorites) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GetFavoritesCallback callback = (GetFavoritesCallback) invocation.getArguments()[0];
                callback.onSuccess(favorites);
                return null;
            }
        }).when(mockRepository).getFavorites(any(GetFavoritesCallback.class));
    }

    private void setupSetFavorites() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                SetFavoritesCallback callback = (SetFavoritesCallback) invocation.getArguments()[0];
                callback.onSuccess();
                return null;
            }
        }).when(mockRepository).setFavorites(any(SetFavoritesCallback.class), anyListOf(Book.class));
    }
}
