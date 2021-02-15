package com.dgpays.movieapi.cammon.helper;

import com.dgpays.movieapi.data.entity.MovieDetailModel;
import com.dgpays.movieapi.data.entity.MovieModel;
import com.dgpays.movieapi.data.entity.VideoModel;
import com.dgpays.movieapi.service.ApiUrl;
import com.dgpays.movieapi.service.MovieApi;
import com.dgpays.movieapi.view.callback.MovieCallBack;
import com.dgpays.movieapi.view.callback.MovieDetailCallBack;
import com.dgpays.movieapi.view.callback.VideoCallBack;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequestHelper {
    public static final HttpRequestHelper REQUEST = new HttpRequestHelper();

    public void loadMovie(MovieCallBack callBack) {
        Gson gson = new com.google.gson.GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MovieModel> call = movieApi.getMovies();
        call.enqueue(new Callback<MovieModel>() {

            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                callBack.onMovieReady(response.body().getMovies());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                callBack.onMovieReady(null);
                t.printStackTrace();
            }
        });
    }

    public void loadMovieDetail(MovieDetailCallBack callBack, int movieID) {
        Gson gson = new com.google.gson.GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MovieDetailModel> call = movieApi.getMovieDetailModel(String.valueOf(movieID));
        call.enqueue(new Callback<MovieDetailModel>() {

            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                callBack.onMovieDetailReady(response.body());
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                callBack.onMovieDetailReady(null);
                t.printStackTrace();
            }
        });

    }
    public void getVideos(VideoCallBack callBack, int movieID) {
        Gson gson = new com.google.gson.GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<VideoModel> call = movieApi.getVideos(String.valueOf(movieID));
        call.enqueue(new Callback<VideoModel>() {

            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {

                if (response.body() != null) {
                    if (response.body().getResults().size() != 0) {
                        callBack.onVideoReady(response.body());
                    }
                    else{
                        callBack.onVideoFailure("Video yüklenemedi");
                    }
                } else {
                    callBack.onVideoFailure("Video yüklenemedi");

                }
            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {
                callBack.onVideoReady(null);
                t.printStackTrace();
            }
        });
    }

    public void searchMovie(MovieCallBack callBack, String searchValue, int page) {
        Gson gson = new com.google.gson.GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(ApiUrl.SEARCH_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final MovieApi movieApi = retrofit.create(MovieApi.class);
        Call<MovieModel> call = movieApi.getSearchMovie(String.valueOf(searchValue), page);
        call.enqueue(new Callback<MovieModel>() {


            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if (response.body() != null) {
                    if (response.body().getMovies().size() != 0) {
                        callBack.onMovieReady(response.body().getMovies());
                    }
                    else{
                        callBack.onMovieFailure("Film bulunamadı.");
                    }
                } else {
                    callBack.onMovieFailure("Film bulunamadı");

                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                callBack.onMovieReady(null);
                t.printStackTrace();
            }
        });
    }
}
