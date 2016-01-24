package com.miguelangelboti.books.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageLinks {

    @Expose
    @SerializedName("smallThumbnail")
    private String smallThumbnail;

    @Expose
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
