package com.skala.videotrainingapp.discovermovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.skala.core.ui.discovermovie.DiscoverMoviePresenter;
import com.skala.core.ui.discovermovie.DiscoverMovieUi;
import com.skala.core.ui.base.BasePresenter;
import com.skala.videotrainingapp.BaseActivity;
import com.skala.videotrainingapp.R;

import javax.inject.Inject;

/**
 * @author Skala
 */
public class DiscoverMovieActivity extends BaseActivity implements DiscoverMovieUi {

    @Inject
    DiscoverMoviePresenter presenter;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter discoverMovieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

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

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadConfig());

        discoverMovieAdapter = new AdapterRecyclerView(presenter.getDiscoverMovie());
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}
