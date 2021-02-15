package com.dgpays.movieapi.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<Movie> movies = null;


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setResults(List<Movie> results) {
        this.movies = movies;
    }

}
