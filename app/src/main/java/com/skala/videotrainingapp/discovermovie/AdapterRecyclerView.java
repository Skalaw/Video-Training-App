package com.skala.videotrainingapp.discovermovie;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skala.core.ui.discovermovie.DiscoverMovieModelView;
import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.image.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Skala
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.MoviesViewHolder> {
    private final List<DiscoverMovieModelView> modelViewList;
    private final ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public AdapterRecyclerView(ImageLoader imageLoader, List<DiscoverMovieModelView> modelView) {
        this.imageLoader = imageLoader;
        this.modelViewList = modelView;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_discover_movie, parent, false); // TODO: add dimens for other sizes
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        DiscoverMovieModelView modelView = modelViewList.get(position);
        holder.bind(modelView);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return modelViewList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(DiscoverMovieModelView discoverMovieModelView);
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private View root;

        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.description)
        public TextView description;

        @BindView(R.id.poster)
        public ImageView poster;

        @BindView(R.id.releaseDate)
        public TextView releaseDate;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            root = itemView;
        }

        public void bind(DiscoverMovieModelView modelView) {
            title.setText(modelView.getTitle());
            description.setText(modelView.getDescription());
            releaseDate.setText(title.getContext().getString(R.string.release_date, modelView.getReleaseDate()));
            imageLoader.load(modelView.getUrlImage(), poster);
            root.setOnClickListener(v -> onItemClickListener.onItemClick(modelView));
        }
    }
}
