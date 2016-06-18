package com.skala.videotrainingapp.DiscoverMovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.ui.DiscoverMovie.DiscoverMoviePresenter;
import com.skala.core.ui.DiscoverMovie.DiscoverMovieUi;
import com.skala.videotrainingapp.R;
import com.skala.videotrainingapp.VideoApp;

import javax.inject.Inject;

import dagger.Module;
import dagger.ObjectGraph;

public class DiscoverMovieActivity extends AppCompatActivity implements DiscoverMovieUi {

    @Inject
    DiscoverMoviePresenter discoverMoviePresenter;
    private ObjectGraph presenterObjectGraph;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterObjectGraph = (ObjectGraph) getLastCustomNonConfigurationInstance(); // TODO: move this to baseActivity
        if (presenterObjectGraph == null) {
            presenterObjectGraph = VideoApp.getApp(this).getObjectGraph().plus(new PresenterModule());
        }
        presenterObjectGraph.inject(this);

        setContentView(R.layout.activity_discover_movie);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // TODO add divider for recycler view
    }

    @Override
    protected void onStart() {
        super.onStart();
        discoverMoviePresenter.onAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        discoverMoviePresenter.onDetach();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenterObjectGraph;
    }

    @Override
    public void displayMovies(DiscoverMovie discoverMovie) {
        RecyclerView.Adapter adapter = new AdapterRecyclerView(discoverMovie);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayError(String message) {
        // TODO: add button with refresh
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Module(injects = {DiscoverMovieActivity.class}, addsTo = VideoApp.AppModule.class)
    public class PresenterModule {
    }
}
