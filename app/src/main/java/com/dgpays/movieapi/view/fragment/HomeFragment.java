package com.dgpays.movieapi.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dgpays.movieapi.cammon.helper.Instance;
import com.dgpays.movieapi.data.entity.Movie;
import com.dgpays.movieapi.databinding.FragmentHomeBinding;
import com.dgpays.movieapi.view.adapter.AdapterListener;
import com.dgpays.movieapi.view.adapter.MovieAdapter;
import com.dgpays.movieapi.view.callback.MovieCallBack;
import com.dgpays.movieapi.view.callback.MovieUiController;

import java.util.List;

import static com.dgpays.movieapi.cammon.helper.HttpRequestHelper.REQUEST;

public class HomeFragment extends BaseFragment implements MovieCallBack, AdapterListener {

    FragmentHomeBinding binding;
    MovieAdapter moviesAdapter;
    MovieUiController ui;
    List<Movie> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        binding.search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ui.onChangeFragment(Instance.THIS.CONSTANT.FRAGMENT_MOVIE_SEARCH,0);
//            }
//        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHttpRequest();
    }

    private void initHttpRequest() {
        REQUEST.loadMovie(this);
    }

    @Override
    public void onMovieReady(List<Movie> movies) {
        if (movies != null) {
            loadMovie(movies);
        } else {
            showToast("data alinirken hata alindi");
        }
    }

    @Override
    public void onMovieFailure(String errorMessage) {
        showToast(errorMessage);
    }

    public void loadMovie(List<Movie> movies) {
        moviesAdapter = new MovieAdapter(getActivity(), this);
        binding.recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerview.setAdapter(moviesAdapter);
        items = movies;
        moviesAdapter.setItems(movies);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClickListener(int position) {
        int movieID = items.get(position).getId();
        ui.onChangeFragment(Instance.THIS.CONSTANT.FRAGMENT_MOVIE_DETAIL, movieID);
    }

    @Override
    public void onLongItemClickListener(int position) { }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            ui = (MovieUiController) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implament AuthUiController");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ui = null;
    }
}
