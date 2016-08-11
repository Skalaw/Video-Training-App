package com.skala.videotrainingapp.discovermovie;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.ui.discovermovie.DiscoverMoviePresenter;
import com.skala.core.ui.discovermovie.DiscoverMovieUi;
import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.base.BaseFragment;
import com.skala.videotrainingapp.home.HomeUi;
import com.skala.videotrainingapp.image.ImageLoader;
import com.skala.videotrainingapp.recyclerview.SpacesItemDecorationColumns;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Skała
 */
public class DiscoverMovieFragment extends BaseFragment implements DiscoverMovieUi {
    public static final String FRAGMENT_TAG = "DiscoverMovieFragment";
    @Inject
    DiscoverMoviePresenter presenter;

    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private AdapterRecyclerView discoverMovieAdapter;

    private HomeUi homeUi;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        homeUi = (HomeUi) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);

        Resources resources = getResources();
        int columns = resources.getInteger(R.integer.discover_movie_list_columns);
        int space = resources.getDimensionPixelSize(R.dimen.adapter_discover_movie_space);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), columns));
        recyclerView.addItemDecoration(new SpacesItemDecorationColumns(space, columns));

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadDiscoverMovie());

        discoverMovieAdapter = new AdapterRecyclerView(imageLoader, presenter.getDiscoverMovie());
        discoverMovieAdapter.setOnItemClickListener(discoverMovieModelView -> homeUi.openMovieDescription(discoverMovieModelView.getId()));
        recyclerView.setAdapter(discoverMovieAdapter);
    }

    @NonNull
    @Override
    protected Object getPresenterModule() {
        return new DiscoverMovieModulePresenter();
    }

    @NonNull
    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void notifyDataChange() {
        discoverMovieAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}
