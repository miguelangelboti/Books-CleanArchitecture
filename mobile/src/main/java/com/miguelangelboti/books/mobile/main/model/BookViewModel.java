package com.miguelangelboti.books.mobile.main.model;

import java.util.List;

public class BookViewModel {

    private final String id;

    private final String title;

    private final List<String> authors;

    private final String publishedDate;

    private final String description;

    private final int pageCount;

    private final String imageUrl;

    private final String webReaderLink;

    public BookViewModel(String id,
                         String title,
                         List<String> authors,
                         String publishedDate,
                         String description,
                         Integer pageCount,
                         String imageUrl,
                         String webReaderLink) {

        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
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

    public Integer getPageCount() {
        return pageCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }
}

