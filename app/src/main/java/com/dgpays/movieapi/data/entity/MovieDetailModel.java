package com.dgpays.movieapi.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailModel {
    @SerializedName("original_title")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    public String getBackdropPath() { return backdropPath; }

    public void setBackdropPath(String backdropPath) { this.backdropPath = backdropPath; }


    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setOriginalLanguage(String originalLanguage) {this.originalLanguage = originalLanguage; }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
