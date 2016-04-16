package com.miguelangelboti.books.data.repository.network;

import javax.inject.Inject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * @author Miguel Ángel Botija.
 */
public class RestClient {

    private static final String BASE_URL = "https://www.googleapis.com/";

    private BooksService booksService;

    @Inject
    public RestClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        booksService = retrofit.create(BooksService.class);
    }

    public BooksService getBooksService() {
        return booksService;
    }
}
