package com.skala.videotrainingapp.discovermovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skala.core.ui.DiscoverMovie.DiscoverMovieModelView;
import com.skala.videotrainingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Skala
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MoviesViewHolder> {
    private final List<DiscoverMovieModelView> modelViewList;

    public AdapterRecyclerView(List<DiscoverMovieModelView> modelView) {
        this.modelViewList = modelView;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_discover_movie, parent, false); // TODO: add dimens for other sizes
        //view = null;
        view.animate();
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        DiscoverMovieModelView modelView = modelViewList.get(position);

        holder.title.setText(modelView.getTitle());
        holder.description.setText(modelView.getDescription());
        Picasso.with(holder.poster.getContext()).load(modelView.getUrlImage()).into(holder.poster); // TODO: remove picasso from here
        holder.releaseDate.setText(holder.title.getContext().getString(R.string.release_date, modelView.getReleaseDate()));
    }

    @Override
    public int getItemCount() {
        return modelViewList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public ImageView poster;
        public TextView releaseDate;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            releaseDate = (TextView) itemView.findViewById(R.id.releaseDate);
        }
    }
}
