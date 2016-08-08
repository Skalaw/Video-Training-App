package com.skala.videotrainingapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.skala.core.ui.base.BasePresenter;
import com.skala.core.ui.base.Ui;

import java.util.UUID;

/**
 * @author Skała
 */
public abstract class BaseFragment extends Fragment implements Ui {
    private static final String STATE_UUID = "STATE_UUID";

    private String fragmentUUID;
    private OnFragmentLifecycle onFragmentLifecycle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFragmentLifecycle = (OnFragmentLifecycle) context;
    }

    @Override
    public void onDetach() {
        onFragmentLifecycle = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentUUID = savedInstanceState == null ? UUID.randomUUID().toString() : savedInstanceState.getString(STATE_UUID);
        onFragmentLifecycle.onFragmentCreate(this, fragmentUUID);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_UUID, fragmentUUID);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPresenter().onAttached(this);
    }

    @Override
    public void onDestroyView() {
        getPresenter().onDetach();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!getActivity().isChangingConfigurations()) {
            onFragmentLifecycle.onFragmentDestroy(fragmentUUID);
        }
    }

    @NonNull
    protected abstract Object getPresenterModule();

    @NonNull
    protected abstract BasePresenter getPresenter();
}
