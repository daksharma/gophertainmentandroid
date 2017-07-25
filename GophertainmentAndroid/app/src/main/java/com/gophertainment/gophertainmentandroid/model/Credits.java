package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dakshsharma on 7/24/17.
 */

public class Credits {
    @SerializedName("cast")
    @Expose
    private List<Cast> Cast = null;

    @SerializedName("crew")
    @Expose
    private List<Crew> Crew = null;

    public List<Cast> getCast() {
        return Cast;
    }

    public void setCast(List<Cast> cast) {
        this.Cast = cast;
    }

    public List<Crew> getCrew() {
        return Crew;
    }

    public void setCrew(List<Crew> crew) {
        this.Crew = crew;
    }
}
