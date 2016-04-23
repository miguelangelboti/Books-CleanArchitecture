package com.miguelangelboti.books.mobile.main.model.mappers;

import com.miguelangelboti.books.domain.entities.Book;
import com.miguelangelboti.books.mobile.main.model.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

/**
 * @author Miguel Ángel Botija.
 */
public class BooksMapper {

    @Inject
    public BooksMapper() {
    }

    /**
     * Transform a list of {@link Book} into a list of {@link BookViewModel}.
     * @param books The list to be transformed.
     * @return The list of {@link BookViewModel}, or {@code null} if {@code books} is not a valid list.
     */
    @Nonnull
    public List<BookViewModel> transform(@Nonnull List<Book> books) {

        List<BookViewModel> result = new ArrayList<>();

        for (Book book : books) {
            BookViewModel bookViewModel = transform(book);
            result.add(bookViewModel);
        }

        return result;
    }

    /**
     * Transform a {@link Book} into a {@link BookViewModel}.
     * @param book The book to be transformed.
     * @return The {@link BookViewModel}, or {@code null} if {@code book} is not valid.
     */
    @Nonnull
    public BookViewModel transform(@Nonnull Book book) {

        String id = book.getId();
        String title = book.getTitle();
        List<String> authors = book.getAuthors();
        String publishedDate = book.getPublishedDate();
        String description = book.getDescription();
        Integer pageCount = book.getPageCount();
        String imageUrl = book.getImageUrl();
        String webReaderLink = book.getWebReaderLink();
        return new BookViewModel(id, title, authors, publishedDate, description, pageCount, imageUrl, webReaderLink);
    }
}
