package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/24/17.
 */

public class Cast {
    @SerializedName("cast_id")
    @Expose
    private int CastId;

    @SerializedName("character")
    @Expose
    private String Character;

    @SerializedName("credit_id")
    @Expose
    private String CreditID;

    @SerializedName("gender")
    @Expose
    private int Gender;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("title")
    @Expose
    private String Title;

    @SerializedName("order")
    @Expose
    private int Order;

    @SerializedName("profile_path")
    @Expose
    private String ProfilePath;

    @SerializedName("poster_path")
    @Expose
    private String PosterPath;


    public int getCastId() {
        return CastId;
    }

    public void setCastId(int castId) {
        CastId = castId;
    }

    public String getCharacter() {
        return Character;
    }

    public void setCharacter(String character) {
        Character = character;
    }

    public String getCreditID() {
        return CreditID;
    }

    public void setCreditID(String creditID) {
        CreditID = creditID;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() { return Title; }

    public void setTitle(String title) { this.Title = title; }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int order) {
        Order = order;
    }

    public String getProfilePath() {
        return ProfilePath;
    }

    public void setProfilePath(String profilePath) {
        ProfilePath = profilePath;
    }

    public String getPosterPath() { return PosterPath; }

    public void setPosterPath(String posterPath) { this.PosterPath = posterPath; }
}
