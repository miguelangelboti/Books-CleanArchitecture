package com.miguelangelboti.books.data.model.mappers;

import com.miguelangelboti.books.data.model.Item;
import com.miguelangelboti.books.data.model.SearchResult;
import com.miguelangelboti.books.data.model.VolumeInfo;
import com.miguelangelboti.books.domain.entities.Book;

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
     * @param searchResult Object Collection to be transformed.
     * @return The list of {@link Book} if valid {@link SearchResult}, false otherwise.
     */
    @Nonnull
    public List<Book> transform(@Nullable SearchResult searchResult) throws IllegalArgumentException {

        if (searchResult == null) {
            throw new IllegalArgumentException();
        }

        List<Book> result = new ArrayList<>();

        int size = searchResult.getTotalItems();
        if (size > 0) {

            result = new ArrayList<>();
            List<Item> items = searchResult.getItems();
            for (Item item : items) {

                Book book = transform(item);
                if (book != null) {
                    result.add(book);
                }
            }
        }

        return result;
    }

    /**
     * Transform a {@link Item} into an {@link Book}.
     * @param item Object to be transformed.
     * @return {@link Book} if valid {@link Item}, null otherwise.
     */
    @Nullable
    public Book transform(@Nullable Item item) {

        Book result = null;
        if (item != null) {

            VolumeInfo volumeInfo = item.getVolumeInfo();
            if (volumeInfo != null) {

                String title = volumeInfo.getTitle();
                List<String> authors = volumeInfo.getAuthors();
                String publishedDate = volumeInfo.getPublishedDate();
                String description = volumeInfo.getDescription();
                Integer pageCount = volumeInfo.getPageCount();
                String thumbnail = (volumeInfo.getImageLinks() != null) ? volumeInfo.getImageLinks().getThumbnail() : null;
                String webReaderLink = (item.getAccessInfo() != null) ? item.getAccessInfo().getWebReaderLink() : null;
                result = new Book(title, authors, publishedDate, description, pageCount, thumbnail, webReaderLink);
            }
        }

        return result;
    }
}
