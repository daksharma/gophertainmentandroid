package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/20/17.
 */

public class Movie {

    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("title")
    @Expose
    private String Title;

    @SerializedName("overview")
    @Expose
    private String Overview;

    @SerializedName("tagline")
    @Expose
    private String tagline;

    @SerializedName("release_date")
    @Expose
    private String ReleaseDate;

    @SerializedName("status")
    @Expose
    private String Status;

    @SerializedName("poster_path")
    @Expose
    private String PosterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String BackdropPath;

    @SerializedName("homepage")
    @Expose
    private String Homepage;

    @SerializedName("belongs_to_collection")
    @Expose
    private BelongToCollection BTC;

    @SerializedName("credits")
    @Expose
    private Credits MovieCredit;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        BackdropPath = backdropPath;
    }

    public String getHomepage() {
        return Homepage;
    }

    public void setHomepage(String homepage) {
        Homepage = homepage;
    }

    public BelongToCollection getBTC() {
        return BTC;
    }

    public void setBTC(BelongToCollection BTC) {
        this.BTC = BTC;
    }

    public Credits getMovieCredit() {
        return MovieCredit;
    }

    public void setMovieCredit(Credits movieCredit) {
        MovieCredit = movieCredit;
    }
}


