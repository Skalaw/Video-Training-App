package com.skala.videotrainingapp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.ui.base.Ui;
import com.skala.videotrainingapp.VideoApp;

import dagger.ObjectGraph;

/**
 * @author Skała
 */
public abstract class BaseActivity extends AppCompatActivity implements Ui {
    private ObjectGraph presenterObjectGraph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenterObjectGraph = (ObjectGraph) getLastCustomNonConfigurationInstance();
        if (presenterObjectGraph == null) {
            presenterObjectGraph = VideoApp.getApp(this).getObjectGraph().plus(getPresenterModule());
        }
        presenterObjectGraph.inject(this);
    }

    @NonNull
    protected abstract Object getPresenterModule();

    @NonNull
    protected abstract BasePresenter getPresenter();

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().onAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onDetach();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenterObjectGraph;
    }
}
