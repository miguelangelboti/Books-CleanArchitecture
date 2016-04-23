package com.miguelangelboti.books.domain.interactor.books;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.domain.executor.ThreadExecutorMock;
import com.miguelangelboti.books.domain.repository.BooksRepository;
import com.miguelangelboti.books.domain.repository.BooksRepository.GetBookCallback;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Miguel Ángel Botija.
 */
public class GetBookInteractorImplTest {

    private GetBookInteractor interactorUnderTest;

    private GetBookInteractor.Callback mockCallback;

    private BooksRepository mockRepository;

    @Before
    public void setup() {

        ThreadExecutorMock executor = new ThreadExecutorMock();
        mockCallback = mock(GetBookInteractor.Callback.class);
        mockRepository = mock(BooksRepository.class);
        interactorUnderTest = new GetBookInteractorImpl(executor, executor, mockRepository);
    }

    @Test
    public void whenBookIdIsNull_RequestFails() {

        interactorUnderTest.execute(mockCallback, null);
        verify(mockRepository, never()).getBook(any(GetBookCallback.class), anyString());
        verify(mockCallback).onError();
    }

    @Test
    public void whenBookIdIsEmpty_RequestFails() {

        interactorUnderTest.execute(mockCallback, "");
        verify(mockRepository, never()).getBook(any(GetBookCallback.class), anyString());
        verify(mockCallback).onError();
    }

    @Test
    public void whenBookIdIsNotEmpty_RequestSuccess() {

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GetBookCallback callback = (GetBookCallback) invocation.getArguments()[0];
                callback.onSuccess(any(Book.class));
                return null;
            }
        }).when(mockRepository).getBook(any(GetBookCallback.class), anyString());

        interactorUnderTest.execute(mockCallback, "bookId");
        verify(mockRepository).getBook(any(GetBookCallback.class), anyString());
        verify(mockCallback).onSuccess(any(Book.class));
    }
}
