package com.skala.videotrainingapp.moviedescription;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skala.core.ui.moviedescription.VideosModelView;
import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.image.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Skala
 */
public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.VideosViewHolder> {
    private final List<VideosModelView> videosModelViews;
    private final ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public AdapterVideos(ImageLoader imageLoader, List<VideosModelView> videosModelViews) {
        this.imageLoader = imageLoader;
        this.videosModelViews = videosModelViews;
    }

    @Override
    public VideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_videos, parent, false);
        return new VideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosViewHolder holder, int position) {
        VideosModelView modelView = videosModelViews.get(position);
        holder.bind(modelView);
    }

    @Override
    public int getItemCount() {
        return videosModelViews.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String url);
    }

    public class VideosViewHolder extends RecyclerView.ViewHolder {
        private final View root;

        @BindView(R.id.title)
        protected TextView title;

        @BindView(R.id.type)
        protected TextView type;

        @BindView(R.id.image)
        protected ImageView image;

        public VideosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            root = itemView;
        }

        public void bind(VideosModelView modelView) {
            title.setText(modelView.getTitle());
            type.setText(modelView.getType());
            imageLoader.load(modelView.getThumbnailUrl(), image);
            root.setOnClickListener(v -> onItemClickListener.onItemClick(modelView.getUrl()));
        }
    }
}
