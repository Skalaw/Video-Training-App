package com.rocksolidapps.movies.moviedescription;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rocksolidapps.core.ui.base.BasePresenter;
import com.rocksolidapps.core.ui.moviedescription.MovieDescriptionModelView;
import com.rocksolidapps.core.ui.moviedescription.MovieDescriptionPresenter;
import com.rocksolidapps.core.ui.moviedescription.MovieDescriptionUi;
import com.rocksolidapps.movies.R;
import com.rocksolidapps.movies.base.BaseFragment;
import com.rocksolidapps.movies.home.HomeUi;
import com.rocksolidapps.movies.image.ImageLoader;
import com.rocksolidapps.movies.recyclerview.HorizontalSpaceItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Skała
 */
public class MovieDescriptionFragment extends BaseFragment implements MovieDescriptionUi {
    public static final String FRAGMENT_TAG = "MovieDescriptionFragment";
    public static final String MOVIE_ID_KEY = "MOVIE_ID";
    private static final int MOVIE_ID_ERROR = -1;
    private static final int ALPHA_BACKGROUND = 192; // 192 from 256 = 75%

    @Inject
    MovieDescriptionPresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.title)
    protected TextView title;

    @BindView(R.id.description)
    protected TextView description;

    @BindView(R.id.voteAverage)
    protected TextView voteAverage;

    @BindView(R.id.releaseDate)
    protected TextView releaseDate;

    @BindView(R.id.titleDescription)
    protected TextView titleDescription;

    @BindView(R.id.titleVideos)
    protected TextView titleVideos;

    @BindView(R.id.padBackground)
    protected LinearLayout padBackground;

    @BindView(R.id.imageBackdrop)
    protected ImageView imageBackdrop;

    @BindView(R.id.imageBackground)
    protected ImageView imageBackground;

    @BindView(R.id.videos)
    protected RecyclerView recyclerViewVideos;
    private AdapterVideos adapterVideos;
    private HomeUi homeUi;

    public static MovieDescriptionFragment newInstance(int id) {
        MovieDescriptionFragment movieDescriptionFragment = new MovieDescriptionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIE_ID_KEY, id);
        movieDescriptionFragment.setArguments(bundle);
        return movieDescriptionFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeUi = (HomeUi) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int movieId = getArguments() == null ? MOVIE_ID_ERROR : getArguments().getInt(MOVIE_ID_KEY, MOVIE_ID_ERROR);
        presenter.setMovieId(movieId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_description, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int space = getResources().getDimensionPixelSize(R.dimen.adapter_videos_space);
        recyclerViewVideos.addItemDecoration(new HorizontalSpaceItemDecoration(space));
        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVideos.setHasFixedSize(true);

        adapterVideos = new AdapterVideos(imageLoader, presenter.getVideosList());
        adapterVideos.setOnItemClickListener(url -> homeUi.openYoutube(url));
        recyclerViewVideos.setAdapter(adapterVideos);

        padBackground.getBackground().setAlpha(ALPHA_BACKGROUND);
    }

    @NonNull
    @Override
    protected Object getPresenterModule() {
        return new MovieDescriptionModulePresenter();
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void displayMovieDescription(MovieDescriptionModelView movieVideos) {
        homeUi.setToolbarTitle(movieVideos.getTitle());
        title.setText(movieVideos.getTitle());
        description.setText(movieVideos.getDescription());
        voteAverage.setText(getString(R.string.vote_average, movieVideos.getVoteAverage()));
        releaseDate.setText(getString(R.string.release_date, movieVideos.getReleaseDate()));
        imageLoader.load(movieVideos.getUrlImageBackdrop(), imageBackdrop);
        imageLoader.load(movieVideos.getUrlImagePoster(), new LoadBitmapFillPalette(imageBackground, padBackground, homeUi));

        adapterVideos.notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        adapterVideos.notifyDataSetChanged();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Log.d("displayError", message);
    }
}
