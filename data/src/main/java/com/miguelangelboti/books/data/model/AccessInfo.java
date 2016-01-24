package com.miguelangelboti.books.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessInfo {

    @Expose
    @SerializedName("country")
    public String country;

    @Expose
    @SerializedName("viewability")
    public String viewability;

    @Expose
    @SerializedName("embeddable")
    public Boolean embeddable;

    @Expose
    @SerializedName("publicDomain")
    public Boolean publicDomain;

    @Expose
    @SerializedName("textToSpeechPermission")
    public String textToSpeechPermission;

    @Expose
    @SerializedName("epub")
    public Epub epub;

    @Expose
    @SerializedName("pdf")
    public Pdf pdf;

    @Expose
    @SerializedName("webReaderLink")
    public String webReaderLink;

    @Expose
    @SerializedName("accessViewStatus")
    public String accessViewStatus;

    @Expose
    @SerializedName("quoteSharingAllowed")
    public Boolean quoteSharingAllowed;

    public String getCountry() {
        return country;
    }

    public String getViewability() {
        return viewability;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public Boolean getPublicDomain() {
        return publicDomain;
    }

    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    public Epub getEpub() {
        return epub;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public String getAccessViewStatus() {
        return accessViewStatus;
    }

    public Boolean getQuoteSharingAllowed() {
        return quoteSharingAllowed;
    }
}
