package com.example.movies_ticketing_aquino_villaester_edp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieItem> movieList;

    public MovieAdapter(List<MovieItem> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    public void onBindViewHolder(@NonNull MovieViewHolder holder, int pos) {
        MovieItem movieItem = movieList.get(pos);

        holder.textview.setText(movieItem.getTitleAndYear());
        holder.imageview.setImageResource(movieItem.getImageId());
    }

    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView textview;

        public MovieViewHolder(@NonNull View itemview) {
            super(itemview);
            imageview = itemview.findViewById(R.id.movie_banner);
            textview = itemview.findViewById(R.id.movie_title);
        }
    }
}