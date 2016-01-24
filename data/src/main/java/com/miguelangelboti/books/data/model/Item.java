package com.miguelangelboti.books.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("etag")
    private String etag;

    @Expose
    @SerializedName("selfLink")
    private String selfLink;

    @Expose
    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    @Expose
    @SerializedName("accessInfo")
    private AccessInfo accessInfo;

    public String getId() {
        return id;
    }

    public String getEtag() {
        return etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }
}
