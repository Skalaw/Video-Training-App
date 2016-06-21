package com.skala.videotrainingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.ui.base.Ui;

import dagger.ObjectGraph;

/**
 * @author Skała
 */
public abstract class BaseActivity extends AppCompatActivity implements Ui {
    private ObjectGraph presenterObjectGraph;
    private BasePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterObjectGraph = (ObjectGraph) getLastCustomNonConfigurationInstance();
        if (presenterObjectGraph == null) {
            presenterObjectGraph = VideoApp.getApp(this).getObjectGraph().plus(getPresenterModule());
        }
        presenterObjectGraph.inject(this);

        setupPresenter(getPresenter());
    }

    private void setupPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    protected abstract Object getPresenterModule();

    @NonNull
    protected abstract BasePresenter getPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onDetach();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenterObjectGraph;
    }
}
