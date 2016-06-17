package com.skala.videotrainingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.skala.core.api.model.DiscoverMovie;
import com.skala.core.ui.DiscoverMovie.DiscoverMoviePresenter;
import com.skala.core.ui.DiscoverMovie.DiscoverMovieUi;

import javax.inject.Inject;

import dagger.Module;
import dagger.ObjectGraph;

public class DiscoverMovieActivity extends AppCompatActivity implements DiscoverMovieUi {

    @Inject
    DiscoverMoviePresenter discoverMoviePresenter;
    private ObjectGraph presenterObjectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenterObjectGraph = (ObjectGraph) getLastCustomNonConfigurationInstance();
        if (presenterObjectGraph == null) {
            presenterObjectGraph = VideoApp.getApp(this).getObjectGraph().plus(new PresenterModule());
        }
        presenterObjectGraph.inject(this);

        setContentView(R.layout.activity_discover_movie);
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
        //TODO show in list/recycler view
        Toast.makeText(this, discoverMovie.getResults().get(0).getOriginalTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Module(injects = {DiscoverMovieActivity.class}, addsTo = VideoApp.AppModule.class)
    public class PresenterModule {
    }
}
