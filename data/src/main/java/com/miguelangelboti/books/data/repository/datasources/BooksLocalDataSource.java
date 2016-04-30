package com.miguelangelboti.books.data.repository.datasources;

import android.content.Context;
import android.support.annotation.NonNull;

import com.miguelangelboti.books.data.utils.Persistence;
import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BooksLocalDataSource implements BooksDataSource {

    private final Context context;

    private List<Book> books;

    @Inject
    public BooksLocalDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void getBooks(@Nonnull GetBooksCallback callback, String query) {
        // Nothing happens here...
    }

    /**
     * Sets the list of retrieved books.
     * @param books The list of books.
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public void getBook(@Nonnull GetBookCallback callback, String bookId) {

        Book result = null;

        int size = (books != null) ? books.size() : 0;
        for (int i = 0; ((i < size) && (result == null)); i++) {

            Book book = books.get(i);
            String id = (book != null) ? book.getId() : null;
            if ((id != null) && id.equals(bookId)) {
                result = book;
            }
        }

        if (result != null) {
            callback.onSuccess(result);
        } else {
            // TODO: Define the exception.
            callback.onError(new Exception());
        }
    }

    @Override
    public void getFavorites(@NonNull GetFavoritesCallback callback) {

        List<Book> favorites = Persistence.loadFavorites(context);
        if (favorites != null) {
            callback.onSuccess(favorites);
        } else {
            // TODO: Define this exception.
            callback.onError(new Exception());
        }
    }

    @Override
    public void setFavorites(@NonNull SetFavoritesCallback callback, List<Book> books) {

        boolean success = Persistence.store(context, books);
        if (success) {
            callback.onSuccess();
        } else {
            // TODO: Define this exception.
            callback.onError(new Exception());
        }
    }
}
