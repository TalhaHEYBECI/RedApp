package com.dgpays.movieapi.view.callback;

import com.dgpays.movieapi.data.entity.Movie;
import com.dgpays.movieapi.data.entity.VideoModel;


public interface VideoCallBack {
    public void onVideoReady(VideoModel videoModel);
    public  void onVideoFailure(String errorMessage);
}
