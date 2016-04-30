package com.miguelangelboti.books.domain.entities;

import java.util.List;

/**
 * @author Miguel Ángel Botija.
 */
public class Book {

    private final String id;

    private final String title;

    private final List<String> authors;

    private final String publishedDate;

    private final String description;

    private final Integer pageCount;

    private final String imageUrl;

    private final String webReaderLink;

    public Book(String id) {
        this(id, null, null, null, null, null, null, null);
    }

    public Book(String id, String title, List<String> authors, String publishedDate, String description, Integer pageCount, String imageUrl, String webReaderLink) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = (pageCount != null) ? pageCount : 0;
        this.imageUrl = imageUrl;
        this.webReaderLink = webReaderLink;
    }

    public String getId() {
        return id;
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

    public String getWebReaderLink() {
        return webReaderLink;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Book)) {
            return false;
        }

        Book book = (Book) object;
        return (id != null) ? id.equals(book.id) : (book.id == null);
    }

    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }
}
