package com.skala.videotrainingapp.DiscoverMovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.api.model.Result;
import com.skala.videotrainingapp.R;

import java.util.List;

/**
 * @author Skala
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MoviesViewHolder> {
    private DiscoverMovie discoverMovie;
    private List<Result> movies;

    public AdapterRecyclerView(DiscoverMovie discoverMovie) {
        this.discoverMovie = discoverMovie;

        movies = discoverMovie.getResults();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_discover_movie, parent, false); // TODO: reposition layout
        MoviesViewHolder holder = new MoviesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Result result = movies.get(position);

        holder.title.setText(result.getTitle());
        holder.description.setText(result.getOverview());
        // TODO: fill image for poster
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView poster;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            poster = (ImageView) itemView.findViewById(R.id.poster);
        }
    }
}
