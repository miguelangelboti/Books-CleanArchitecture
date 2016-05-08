package com.miguelangelboti.books.domain.interactor.favorites;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.ThreadExecutorMock;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetFavoritesCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Miguel Ángel Botija.
 */
public class IsFavoriteInteractorImplTest {

    private IsFavoriteInteractor interactorUnderTest;

    private IsFavoriteInteractor.Callback mockCallback;

    private BooksRepository mockRepository;

    @Before
    public void setup() {

        ThreadExecutorMock executor = new ThreadExecutorMock();
        mockCallback = mock(IsFavoriteInteractor.Callback.class);
        mockRepository = mock(BooksRepository.class);
        interactorUnderTest = new IsFavoriteInteractorImpl(executor, executor, mockRepository);
    }

    @Test
    public void whenBookIdIsEmpty_RequestFails() {

        interactorUnderTest.execute(mockCallback, "");
        verify(mockRepository, never()).getFavorites(any(GetFavoritesCallback.class));
        verify(mockCallback, never()).onSuccess(anyBoolean());
        verify(mockCallback).onError();
    }

    @Test
    public void whenBookIdIsNotEmptyAndIsNotFavorite_RequestSuccess() {

        List<Book> favorites = new ArrayList<>();

        setupGetFavorites(favorites);

        interactorUnderTest.execute(mockCallback, "1");
        verify(mockCallback, never()).onError();
        verify(mockCallback).onSuccess(eq(false));
    }

    @Test
    public void whenBookIdIsNotEmptyAndIsFavorite_RequestSuccess() {

        List<Book> favorites = new ArrayList<>();
        Book book = new Book("1");
        favorites.add(book);

        setupGetFavorites(favorites);

        interactorUnderTest.execute(mockCallback, "1");
        verify(mockCallback, never()).onError();
        verify(mockCallback).onSuccess(eq(true));
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
}
