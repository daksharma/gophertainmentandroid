package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/22/17.
 */

public class MultiSearchResult {

    @SerializedName("id")
    @Expose
    private int ID;


    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("title")
    @Expose

    private String Title;

    @SerializedName("overview")
    @Expose
    private String Overview;

    @SerializedName("media_type")
    @Expose
    private String MediaType;

    @SerializedName("popularity")
    @Expose
    private double Popularity;

    @SerializedName("first_air_date")
    @Expose
    private String FirstAirDate;

    @SerializedName("release_date")
    @Expose
    private String ReleaseDate;

    @SerializedName("original_language")
    @Expose
    private String OriginalLanguage;

    @SerializedName("poster_path")
    @Expose
    private String PosterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String BackdropPath;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public void setMediaType(String mediaType) {
        MediaType = mediaType;
    }

    public void setPopularity(double popularity) {
        Popularity = popularity;
    }

    public void setFirstAirDate(String firstAirDate) {
        FirstAirDate = firstAirDate;
    }

    public void setReleaseDate(String releaseDate) {
        ReleaseDate = releaseDate;
    }

    public void setOriginalLanguage(String originalLanguage) {
        OriginalLanguage = originalLanguage;
    }

    public void setPosterPath(String posterPath) {
        PosterPath = posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        BackdropPath = backdropPath;
    }


    // GETTERs
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getTitle() {
        return Title;
    }

    public String getOverview() {
        return Overview;
    }

    public String getMediaType() {
        return MediaType;
    }

    public double getPopularity() {
        return Popularity;
    }

    public String getFirstAirDate() {
        return FirstAirDate;
    }

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public String getOriginalLanguage() {
        return OriginalLanguage;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }
}
