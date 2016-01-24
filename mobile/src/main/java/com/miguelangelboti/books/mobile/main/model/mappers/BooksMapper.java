package com.miguelangelboti.books.mobile.main.model.mappers;

import com.miguelangelboti.books.data.model.SearchResult;
import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Miguel Ángel Botija.
 */
public class BooksMapper {

    /**
     * Transform a {@link SearchResult} into a list of {@link Book}.
     * @param books The list to be transformed.
     * @return The list of {@link Book} if valid {@link SearchResult}, false otherwise.
     */
    @Nonnull
    public List<BookViewModel> transform(@Nullable List<Book> books) {

        List<BookViewModel> result = new ArrayList<>();

        if (books != null) {

            for (Book book : books) {

                String title = book.getTitle();
                List<String> authors = book.getAuthors();
                String publishedDate = book.getPublishedDate();
                String description = book.getDescription();
                Integer pageCount = book.getPageCount();
                String imageUrl = book.getImageUrl();
                String webReaderLink = book.getWebReaderLink();
                BookViewModel bookViewModel = new BookViewModel(title, authors, publishedDate, description, pageCount, imageUrl, webReaderLink);
                result.add(bookViewModel);
            }
        }

        return result;
    }
}
