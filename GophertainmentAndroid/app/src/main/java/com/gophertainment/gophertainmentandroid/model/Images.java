package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class Images {

    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles = null;

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

}
