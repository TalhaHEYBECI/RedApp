package com.dgpays.movieapi.view.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgpays.movieapi.data.entity.Movie;
import com.dgpays.movieapi.data.entity.MovieDetailModel;
import com.dgpays.movieapi.databinding.ItemMovieBinding;
import com.dgpays.movieapi.service.ApiUrl;
import com.dgpays.movieapi.view.adapter.holder.MovieHolder;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_CLASSIC = 3;
    Activity activity;
    List<Movie> items;
    AdapterListener listener;

    public MovieAdapter(Activity cont, AdapterListener listener) {
        this.activity = cont;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_CLASSIC:
                ItemMovieBinding binding = ItemMovieBinding.inflate(inflater, parent, false);
                return new MovieHolder(binding, listener);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieHolder) {
            ((MovieHolder) holder).bind((Movie) items.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items != null || items.size() > 0) {
            return TYPE_CLASSIC;
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        if (items == null || items.size() == 0) {
            return 1;
        }
        return items.size();
    }

    public void setItems(List<Movie> items) {
        this.items = items;
        for (int i = 0; i < items.size(); i++) {
            this.items.get(i).setPosterPath(ApiUrl.IMAGE_URL + items.get(i).getPosterPath());
            this.items.get(i).setBackdropPath(ApiUrl.IMAGE_URL+items.get(i).getBackdropPath());
        }
        notifyDataSetChanged();
    }

}