package com.dgpays.movieapi.view.adapter.holder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dgpays.movieapi.data.entity.Movie;
import com.dgpays.movieapi.databinding.ItemMovieBinding;
import com.dgpays.movieapi.view.adapter.AdapterListener;

public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemMovieBinding binding;
    AdapterListener listener;

    public MovieHolder(ItemMovieBinding binding, AdapterListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
        binding.getRoot().setOnClickListener(this);
    }

    public void bind(Movie item) {
        binding.setItem(item);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClickListener(getAdapterPosition());
    }
}
