package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class Person {

    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("biography")
    @Expose
    private String Biography;

    @SerializedName("birthday")
    @Expose
    private String Birthday;

    @SerializedName("deathday")
    @Expose
    private String Deathday;

    @SerializedName("gender")
    @Expose
    private int Gender;

    @SerializedName("homepage")
    @Expose
    private String Homepage;

    @SerializedName("place_of_birth")
    @Expose
    private String PlaceOfBirth;

    @SerializedName("profile_path")
    @Expose
    private String ProfilePath;

    @SerializedName("credits")
    @Expose
    private Credits Credits;

    @SerializedName("images")
    @Expose
    private Images images;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getBiography() { return Biography; }

    public void setBiography(String biography) { this.Biography = biography; }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        this.Birthday = birthday;
    }

    public String getDeathday() {
        return Deathday;
    }

    public void setDeathday(String deathday) {
        this.Deathday = deathday;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        this.Gender = gender;
    }

    public String getHomepage() {
        return Homepage;
    }

    public void setHomepage(String homepage) {
        this.Homepage = homepage;
    }

    public String getPlaceOfBirth() {
        return PlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.PlaceOfBirth = placeOfBirth;
    }

    public String getProfilePath() {
        return ProfilePath;
    }

    public void setProfilePath(String profilePath) {
        this.ProfilePath = profilePath;
    }

    public com.gophertainment.gophertainmentandroid.model.Credits getCredits() {
        return Credits;
    }

    public void setCredits(com.gophertainment.gophertainmentandroid.model.Credits credits) {
        this.Credits = credits;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
