package com.dgpays.movieapi.data.entity;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dgpays.movieapi.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("vote_average")
    @Expose
    private String voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    public String getBackdropPath() { return backdropPath; }

    public void setBackdropPath(String backdropPath) { this.backdropPath = backdropPath; }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {this.originalLanguage = originalLanguage; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String imageURL) {
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions())
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);
    }
}
