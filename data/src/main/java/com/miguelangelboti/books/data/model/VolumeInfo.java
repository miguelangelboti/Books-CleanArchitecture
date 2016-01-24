package com.miguelangelboti.books.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VolumeInfo {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("authors")
    private List<String> authors = new ArrayList<>();

    @Expose
    @SerializedName("publishedDate")
    private String publishedDate;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("pageCount")
    private Integer pageCount;

    @Expose
    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    @Expose
    @SerializedName("language")
    private String language;

    @Expose
    @SerializedName("previewLink")
    private String previewLink;

    @Expose
    @SerializedName("infoLink")
    private String infoLink;

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

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }
}
