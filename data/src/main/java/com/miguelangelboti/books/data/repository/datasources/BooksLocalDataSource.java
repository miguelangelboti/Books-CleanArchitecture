package com.miguelangelboti.books.data.repository.datasources;

import com.miguelangelboti.books.domain.entities.Book;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BooksLocalDataSource implements BooksDataSource {

    private List<Book> books;

    @Inject
    public BooksLocalDataSource() {
    }

    @Override
    public void getBooks(@Nonnull GetBooksCallback callback, String query) {
        // TODO: Nothing happens here...
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
}
