package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dakshsharma on 7/24/17.
 */

public class TvShow {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;

    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;

    @SerializedName("number_of_episodes")
    @Expose
    private Integer numberOfEpisodes;

    @SerializedName("number_of_seasons")
    @Expose
    private Integer numberOfSeasons;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("in_production")
    @Expose
    private Boolean inProduction;

    @SerializedName("seasons")
    @Expose
    private List<ShowSeason> seasons = null;

    @SerializedName("created_by")
    @Expose
    private List<ShowCreatedBy> createdBy = null;

    @SerializedName("credits")
    @Expose
    private Credits credits;



    public Integer getId() {
        return id;
    }

    public String getName() { return Name; }

    public void setName(String name) { this.Name = name; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public Integer getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(Integer numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<ShowSeason> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<ShowSeason> seasons) {
        this.seasons = seasons;
    }

    public List<ShowCreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<ShowCreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }
}
