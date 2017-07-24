package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dakshsharma on 7/23/17.
 */

public class JsonResult {
    @SerializedName("results")
    @Expose
    private List<MultiSearchResult> results;

    public List<MultiSearchResult> getResults() { return results; }
    public void setResults(List<MultiSearchResult> results) { this.results = results; }
}
