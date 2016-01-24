package com.miguelangelboti.books.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    @Expose
    @SerializedName("kind")
    private String kind;

    @Expose
    @SerializedName("totalItems")
    private int totalItems;

    @Expose
    @SerializedName("items")
    private List<Item> items = new ArrayList<>();

    public String getKind() {
        return kind;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<Item> getItems() {
        return items;
    }
}
