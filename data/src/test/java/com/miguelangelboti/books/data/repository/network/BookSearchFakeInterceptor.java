package com.miguelangelboti.books.data.repository.network;

import java.io.IOException;
import java.net.URI;

import com.miguelangelboti.books.data.utils.JsonUtils;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

/**
 * @author Miguel Ángel Botija.
 */
public class BookSearchFakeInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final URI uri = chain.request().uri();

        // Get Query String.
        final String query = uri.getQuery();

        // Parse the Query String.
        String responseString;
        int statusCode;

        final String[] parsedQuery = (query != null) ? query.split("=") : null;
        if ((parsedQuery == null) || (parsedQuery.length == 0)) {

            responseString = JsonUtils.getStringFromFile("retrieve_books_missing_query.json");
            statusCode = 400;

        } else if ((parsedQuery.length == 1) || (parsedQuery[0].equalsIgnoreCase("q") && parsedQuery[1].length() == 0)) {

            responseString = JsonUtils.getStringFromFile("retrieve_books_empty_query.json");
            statusCode = 304;

        } else {

            responseString = JsonUtils.getStringFromFile("retrieve_books.json");
            statusCode = 200;
        }

        return new Response.Builder()
                .code(statusCode)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }
}
