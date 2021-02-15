package com.dgpays.movieapi.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dgpays.movieapi.R;
import com.dgpays.movieapi.data.entity.MovieDetailModel;
import com.dgpays.movieapi.data.entity.VideoModel;
import com.dgpays.movieapi.databinding.FragmentMovieDetailBinding;
import com.dgpays.movieapi.service.ApiUrl;
import com.dgpays.movieapi.view.callback.MovieDetailCallBack;
import com.dgpays.movieapi.view.callback.VideoCallBack;

import java.net.URL;

import static com.dgpays.movieapi.cammon.helper.HttpRequestHelper.REQUEST;


public class MovieDetailFragment extends BaseFragment implements MovieDetailCallBack, VideoCallBack {

    FragmentMovieDetailBinding binding;
    int movieID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movieID = getArguments().getInt("message");
        initHttpRequest();
    }

    private void initHttpRequest() {
        REQUEST.loadMovieDetail(this, movieID);
        REQUEST.getVideos(this, movieID);

    }

    @Override
    public void onMovieDetailReady(MovieDetailModel movieDetail) {
        if (movieDetail != null) {
            getMovieDetail(movieDetail);
        } else {
            showToast("data alinirken hata alindi");
        }
    }

    public void getMovieDetail(MovieDetailModel movieDetail) {
        binding.textViewMovieTitle.setText(movieDetail.getName());
        binding.textMovieLanguage.setText(movieDetail.getOriginalLanguage());
        binding.textMovieOverview.setText(movieDetail.getOverview());
        binding.textMovieRelaseDate.setText(movieDetail.getReleaseDate());
        binding.textMovieVoteAvarage.setText(String.valueOf(movieDetail.getVoteAverage()));
        Glide.with(binding.imageViewMovie.getContext())
                .setDefaultRequestOptions(new RequestOptions())
                .load(ApiUrl.IMAGE_URL + movieDetail.getPosterPath())
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageViewMovie);

        Glide.with(binding.bgImg.getContext())
                .setDefaultRequestOptions(new RequestOptions())
                .load(ApiUrl.IMAGE_URL + movieDetail.getBackdropPath())
                .into(binding.bgImg);


    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onVideoReady(VideoModel videoModel) {
        if (videoModel != null) {
            getKey(videoModel);
        } else {
            showToast("data alinirken hata alindi");
        }
    }

    @Override
    public void onVideoFailure(String errorMessage) {
        showToast(errorMessage);
    }

    private void getKey(VideoModel videoModel) {
        binding.videoView.getSettings().setJavaScriptEnabled(true);
        binding.videoView.loadUrl("https://www.youtube.com/embed/" + videoModel.getResults().get(0).getKey() + "?autoplay=1&vq=small");
        binding.videoView.setWebChromeClient(new WebChromeClient());
    }
}
