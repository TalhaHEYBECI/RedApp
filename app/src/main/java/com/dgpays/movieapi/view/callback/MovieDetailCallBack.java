package com.dgpays.movieapi.view.callback;

import com.dgpays.movieapi.data.entity.MovieDetailModel;

public interface MovieDetailCallBack {
    public void onMovieDetailReady(MovieDetailModel movieDetail);
}
