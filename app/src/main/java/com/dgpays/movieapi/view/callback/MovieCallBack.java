package com.dgpays.movieapi.view.callback;

import com.dgpays.movieapi.data.entity.Movie;

import java.util.List;

public interface MovieCallBack {
    public void onMovieReady(List<Movie> movies);
    public void onMovieFailure(String errorMessage);
}
