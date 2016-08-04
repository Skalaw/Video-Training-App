package com.skala.videotrainingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.ui.base.Ui;

import dagger.ObjectGraph;

/**
 * @author Skała
 */
public abstract class BaseFragment extends Fragment implements Ui {
    private static final String STATE_UUID = "STATE_UUID";

    private BasePresenter presenter;
    private String fragmentUUID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            fragmentUUID = java.util.UUID.randomUUID().toString();
        } else {
            fragmentUUID = savedInstanceState.getString(STATE_UUID);
        }

        BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
        ObjectGraph objectGraph = activity.getObjectGraph(this, fragmentUUID);
        objectGraph.inject(this);
        Log.d("ObjectGraph", "ObjectGraph: " + objectGraph); // todo remove this when always objectgraph is the same
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_UUID, fragmentUUID);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupPresenter(getPresenter());
        presenter.onAttached(this);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!getActivity().isChangingConfigurations()) {
            BaseFragmentActivity activity = (BaseFragmentActivity) getActivity();
            activity.onFragmentDestroy(fragmentUUID);
        }
    }

    private void setupPresenter(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    protected abstract Object getPresenterModule();

    @NonNull
    protected abstract BasePresenter getPresenter();
}
