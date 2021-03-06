package com.miguelangelboti.books.domain.interactor.search;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.ThreadExecutorMock;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.BookSearchCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Miguel Ángel Botija.
 */
public class SearchInteractorImplTest {

    private SearchInteractor interactorUnderTest;

    private SearchInteractor.Callback mockCallback;

    private BooksRepository mockRepository;

    @Before
    public void setup() {

        ThreadExecutorMock executor = new ThreadExecutorMock();
        mockCallback = mock(SearchInteractor.Callback.class);
        mockRepository = mock(BooksRepository.class);
        interactorUnderTest = new SearchInteractorImpl(executor, executor, mockRepository);
    }

    @Test
    public void whenQueryIsNull_RequestFails() {

        interactorUnderTest.execute(mockCallback, null);
        verify(mockRepository, never()).doBookSearch(any(BookSearchCallback.class), anyString());
        verify(mockCallback).onError();
    }

    @Test
    public void whenQueryIsEmpty_RequestFails() {

        interactorUnderTest.execute(mockCallback, "");
        verify(mockRepository, never()).doBookSearch(any(BookSearchCallback.class), anyString());
        verify(mockCallback).onError();
    }

    @Test
    public void whenQueryIsNotEmpty_RequestSuccess() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                BookSearchCallback callback = (BookSearchCallback) invocation.getArguments()[0];
                callback.onSuccess(anyListOf(Book.class));
                return null;
            }
        }).when(mockRepository).doBookSearch(any(BookSearchCallback.class), anyString());

        interactorUnderTest.execute(mockCallback, "book");
        verify(mockRepository).doBookSearch(any(BookSearchCallback.class), anyString());
        verify(mockCallback).onSuccess(anyListOf(Book.class));
    }
}
