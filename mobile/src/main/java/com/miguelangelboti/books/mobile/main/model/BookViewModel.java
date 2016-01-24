package com.miguelangelboti.books.mobile.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookViewModel implements Parcelable {

    private final String title;
    private final List<String> authors;
    private final String publishedDate;
    private final String description;
    private final int pageCount;
    private final String imageUrl;

    public BookViewModel(String title, List<String> authors, String publishedDate, String description, Integer pageCount, String imageUrl) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.pageCount = pageCount;
        this.imageUrl = imageUrl;
    }

    protected BookViewModel(Parcel in) {
        title = in.readString();
        authors = in.createStringArrayList();
        publishedDate = in.readString();
        description = in.readString();
        pageCount = in.readInt();
        imageUrl = in.readString();
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

    public static final Creator<BookViewModel> CREATOR = new Creator<BookViewModel>() {
        @Override
        public BookViewModel createFromParcel(Parcel in) {
            return new BookViewModel(in);
        }

        @Override
        public BookViewModel[] newArray(int size) {
            return new BookViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(authors);
        dest.writeString(publishedDate);
        dest.writeString(description);
        dest.writeInt(pageCount);
        dest.writeString(imageUrl);
    }
}
