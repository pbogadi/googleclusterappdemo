package com.mike.givemewingzz.mapsclusterdemo.model.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class BaseModel extends RealmObject {

    @SerializedName("html_attributions")
    private RealmList<HtmlAttributions> htmlAttributions;

    private RealmList<Results> results;

    @SerializedName("next_page_token")
    private String nextPageToken;

    private String status;

    public RealmList<HtmlAttributions> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(RealmList<HtmlAttributions> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public RealmList<Results> getResults() {
        return results;
    }

    public void setResults(RealmList<Results> results) {
        this.results = results;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
