package com.dgpays.movieapi.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoModel {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoResults> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VideoResults> getResults() {
        return results;
    }

    public void setResults(List<VideoResults> results) {
        this.results = results;
    }

}

