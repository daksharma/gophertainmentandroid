package com.gophertainment.gophertainmentandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dakshsharma on 7/29/17.
 */

public class Profile {
    @SerializedName("file_path")
    @Expose
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
