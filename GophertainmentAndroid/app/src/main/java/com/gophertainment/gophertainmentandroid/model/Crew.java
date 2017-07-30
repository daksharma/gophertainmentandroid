package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/24/17.
 */

public class Crew {
    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("credit_id")
    @Expose
    private String CreditID;

    @SerializedName("department")
    @Expose
    private String Department;

    @SerializedName("gender")
    @Expose
    private int Gender;

    @SerializedName("job")
    @Expose
    private String Job;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("title")
    @Expose
    private String Title;

    @SerializedName("profile_path")
    @Expose
    private String ProfilePath;

    @SerializedName("poster_path")
    @Expose
    private String PosterPath;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCreditID() {
        return CreditID;
    }

    public void setCreditID(String creditID) {
        CreditID = creditID;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() { return Title; }

    public void setTitle(String title) { this.Title = title; }

    public String getProfilePath() {
        return ProfilePath;
    }

    public void setProfilePath(String profilePath) {
        ProfilePath = profilePath;
    }

    public String getPosterPath() { return PosterPath; }

    public void setPosterPath(String posterPath) { this.PosterPath = posterPath; }
}

