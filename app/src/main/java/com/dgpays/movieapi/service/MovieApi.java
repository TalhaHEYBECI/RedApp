package com.dgpays.movieapi.service;

import com.dgpays.movieapi.data.entity.MovieDetailModel;
import com.dgpays.movieapi.data.entity.MovieModel;
import com.dgpays.movieapi.data.entity.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("popular?api_key=24b2d9572854e8526de99287fdcaa98a")
    Call<MovieModel> getMovies();

    @GET("{movieID}?api_key=24b2d9572854e8526de99287fdcaa98a&language=en-US")
    Call<MovieDetailModel> getMovieDetailModel(@Path(value = "movieID", encoded = true) String movieID);

    @GET("search/movie?api_key=24b2d9572854e8526de99287fdcaa98a&language=en-US")
    Call<MovieModel> getSearchMovie(@Query("query") String searchValue,@Query("page")int page);

    @GET("{movieID}/videos?api_key=24b2d9572854e8526de99287fdcaa98a&language=en-US")
    Call<VideoModel> getVideos(@Path(value="movieID",encoded = true)String movieID);


}
