package com.miguelangelboti.books.data.repository.datasources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.miguelangelboti.books.data.model.SearchResult;
import com.miguelangelboti.books.data.model.mappers.BooksMapper;
import com.miguelangelboti.books.data.repository.network.BooksService;
import com.miguelangelboti.books.data.repository.network.RestClient;
import com.miguelangelboti.books.domain.entities.Book;

import retrofit.Call;
import retrofit.Response;

/**
 * {@link BooksDataSource} implementation based on connections to the network.
 */
@Singleton
public class BooksNetworkDataSource implements BooksDataSource {

    private RestClient restClient;

    @Inject
    public BooksNetworkDataSource(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void getBooks(final Callback callback, final String query) {

        try {

            BooksService booksService = restClient.getBooksService();
            Call<SearchResult> searchResultCall = booksService.doBooksSearch(query);
            Response<SearchResult> response = searchResultCall.execute();
            SearchResult searchResult = response.body();

            BooksMapper mapper = new BooksMapper();
            List<Book> books = mapper.transform(searchResult);
            callback.onSuccess(books);

        } catch (Exception exception) {
            callback.onError(exception);
        }
    }
}
