package com.miguelangelboti.books.data.repository.datasources;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.miguelangelboti.books.data.repository.network.BooksService;
import com.miguelangelboti.books.data.repository.network.BookSearchFakeInterceptor;
import com.miguelangelboti.books.data.repository.network.RestClient;
import com.miguelangelboti.books.domain.entities.Book;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Miguel Ángel Botija.
 */
public class BooksNetworkDataSourceTest {

    private static final String BASE_URL = "https://www.googleapis.com/";

    private BooksNetworkDataSource dataSourceUnderTest;

    private BooksDataSource.Callback mockCallback;

    @Before
    public void setup() {

        RestClient restClient = mock(RestClient.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                OkHttpClient client = new OkHttpClient();
                client.interceptors().add(new BookSearchFakeInterceptor());

                return new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
                        .create(BooksService.class);
            }
        }).when(restClient).getBooksService();

        mockCallback = mock(BooksDataSource.Callback.class);
        dataSourceUnderTest = new BooksNetworkDataSource(restClient);
    }

    @Test
    public void whenQueryIsNull_RequestFails() {

        dataSourceUnderTest.getBooks(mockCallback, null);
        verify(mockCallback).onError(Matchers.<Exception>any());
        verify(mockCallback, never()).onSuccess(anyListOf(Book.class));
    }

    @Test
    public void whenQueryIsEmpty_RequestFails() {

        dataSourceUnderTest.getBooks(mockCallback, "");
        verify(mockCallback).onError(Matchers.<Exception>any());
        verify(mockCallback, never()).onSuccess(anyListOf(Book.class));
    }

    @Test
    public void whenQueryIsNotEmpty_RequestSuccess() {

        dataSourceUnderTest.getBooks(mockCallback, "android");
        verify(mockCallback).onSuccess(anyListOf(Book.class));
        verify(mockCallback, never()).onError(Matchers.<Exception>any());
    }

    @Test
    public void whenQueryHasSeveralWords_RequestSuccess() {

        dataSourceUnderTest.getBooks(mockCallback, "android android");
        verify(mockCallback).onSuccess(anyListOf(Book.class));
        verify(mockCallback, never()).onError(Matchers.<Exception>any());
    }
}
