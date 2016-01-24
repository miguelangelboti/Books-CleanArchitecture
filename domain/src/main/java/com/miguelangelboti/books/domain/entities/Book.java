package com.miguelangelboti.books.domain.entities;

import java.util.List;

/**
 * @author Miguel Ángel Botija.
 */
public class Book {

    private final String title;
    private final List<String> authors;
    private final String publishedDate;
    private final String description;
    private final Integer pageCount;
    private final String imageUrl;

    public Book(String title, List<String> authors, String publishedDate, String description, Integer pageCount, String imageUrl) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = (pageCount != null) ? pageCount : 0;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
