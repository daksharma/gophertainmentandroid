package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/24/17.
 */

public class BelongToCollection {
    @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("poster_path")
    @Expose
    private String PosterPath;

    @SerializedName("backdrop_path")
    @Expose
    private String BackdropPath;
}