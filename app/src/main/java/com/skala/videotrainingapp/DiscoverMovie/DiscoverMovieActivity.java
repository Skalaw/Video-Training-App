package com.skala.videotrainingapp.discovermovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.skala.core.ui.DiscoverMovie.DiscoverMoviePresenter;
import com.skala.core.ui.DiscoverMovie.DiscoverMovieUi;
import com.skala.core.ui.base.BasePresenter;
import com.skala.videotrainingapp.BaseActivity;
import com.skala.videotrainingapp.R;

import javax.inject.Inject;

/**
 * @author Skala
 */
public class DiscoverMovieActivity extends BaseActivity implements DiscoverMovieUi {

    @Inject
    DiscoverMoviePresenter discoverMoviePresenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter discoverMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_discover_movie);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalDividerRecyclerView(this));
        // TODO make discoverMovieAdapter clickable

        discoverMovieAdapter = new AdapterRecyclerView(discoverMoviePresenter.getDiscoverMovie());
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
        return discoverMoviePresenter;
    }

    @Override
    public void notifyDataChange() {
        discoverMovieAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayError(String message) {
        // TODO: add button with refresh
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
