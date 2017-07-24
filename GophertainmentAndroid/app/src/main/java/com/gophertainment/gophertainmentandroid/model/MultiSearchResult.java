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

    @SerializedName("profile_path")
    @Expose
    private String ProfilePath;

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
        this.Name = name;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setOverview(String overview) {
        this.Overview = overview;
    }

    public void setMediaType(String mediaType) {
        this.MediaType = mediaType;
    }

    public void setPopularity(double popularity) {
        this.Popularity = popularity;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.FirstAirDate = firstAirDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.ReleaseDate = releaseDate;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.OriginalLanguage = originalLanguage;
    }

    public void setProfilePath(String profilePath) {
        this.ProfilePath = profilePath;
    }

    public void setPosterPath(String posterPath) {
        this.PosterPath = posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.BackdropPath = backdropPath;
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

    public String getProfilePath() {
        return ProfilePath;
    }

    public String getPosterPath() {
        return PosterPath;
    }

    public String getBackdropPath() {
        return BackdropPath;
    }
}
