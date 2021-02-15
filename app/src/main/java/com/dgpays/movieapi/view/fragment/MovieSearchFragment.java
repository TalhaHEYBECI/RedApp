package com.dgpays.movieapi.view.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dgpays.movieapi.R;
import com.dgpays.movieapi.cammon.helper.Instance;
import com.dgpays.movieapi.data.entity.MovieModel;
import com.dgpays.movieapi.data.entity.Movie;
import com.dgpays.movieapi.databinding.FragmentMovieSearchBinding;
import com.dgpays.movieapi.service.ApiUrl;
import com.dgpays.movieapi.view.adapter.AdapterListener;
import com.dgpays.movieapi.view.adapter.MovieAdapter;
import com.dgpays.movieapi.view.callback.MovieCallBack;
import com.dgpays.movieapi.view.callback.MovieDetailCallBack;
import com.dgpays.movieapi.view.callback.MovieUiController;
import com.google.android.material.slider.Slider;

import java.util.List;

import static com.dgpays.movieapi.cammon.helper.HttpRequestHelper.REQUEST;


public class MovieSearchFragment extends BaseFragment implements MovieCallBack,
        AdapterListener,
        View.OnClickListener,
        View.OnKeyListener{

    List<Movie> items;
    FragmentMovieSearchBinding binding;
    MovieAdapter moviesAdapter;
    MovieUiController ui;
    String searchValue = "",searchItem="";
    int page = 1;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMovieSearchBinding.inflate(inflater, container, false);
        binding.btnSearch.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);
        binding.forwardBtn.setOnClickListener(this::onClick);
        binding.edittxt.setOnKeyListener(this::onKey);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchItem = getArguments().getString("searchItem");
        initHttpRequest(searchItem,page);

    }

    private void initHttpRequest(String searchValue, int page) {
        REQUEST.searchMovie(this, searchValue, page);
        //REQUEST.searchMovie(this,searchItem,page);
    }


    @Override
    public void onMovieReady(List<Movie> movies) {
        if (movies != null) {
            searchMovie(movies);
        } else {
            showToast("data alinirken hata alindi");
        }
    }


    @Override
    public void onMovieFailure(String errorMessage) {
        page = 1;
        showToast(errorMessage);
    }

    public void searchMovie(List<Movie> movies) {
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
    public void onLongItemClickListener(int position) {
    }


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

    @Override
    public void onClick(View v) {

        searchValue = binding.edittxt.getText().toString();

        if (v == binding.btnSearch) {
            page = 1;

            if (!searchValue.equals("")) {
                initHttpRequest(searchValue, page);

            } else {
                showToast("Lütfen film adı giriniz");
            }

        } else if (v == binding.forwardBtn) {
            page++;
            if(!searchValue.equals("")){
                initHttpRequest(searchValue, page);
            }else if(!searchItem.equals("")){
                initHttpRequest(searchItem, page);
            }else {
                showToast("Lutfen Film Adı Giriniz!");
            }

        } else if (v == binding.backBtn) {
            if (page > 1) {
                page--;
                initHttpRequest(searchValue, page);
            } else {
                showToast("İlk sayfadasınız");
            }

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (v == binding.edittxt) {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                hideSoftKeyboard();
                searchValue = binding.edittxt.getText().toString();
                if (!searchValue.equals("")) {
                    initHttpRequest(searchValue, page);

                } else {
                    showToast("Lütfen film adı giriniz");
                }
            }
            return true;
        }

        return false;
    }


}
