package com.miguelangelboti.books.data.repository.network;

import com.miguelangelboti.books.data.model.SearchResult;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Miguel Ángel Botija.
 */
public interface BooksService {

    @GET("books/v1/volumes")
    Call<SearchResult> doBooksSearch(@Query("q") String query);
}
