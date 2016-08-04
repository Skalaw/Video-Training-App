package com.skala.videotrainingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import dagger.ObjectGraph;

/**
 * @author Skała
 */
public class BaseFragmentActivity extends AppCompatActivity { // todo interface for fragmentlifecycle
    private Map<String, ObjectGraph> fragmentPresenterGraphs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //noinspection unchecked
        fragmentPresenterGraphs = (Map<String, ObjectGraph>) getLastCustomNonConfigurationInstance();
        if (fragmentPresenterGraphs == null) {
            fragmentPresenterGraphs = new HashMap<>();
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return fragmentPresenterGraphs;
    }

    public ObjectGraph getPresenterGraphForFragment(String id) {
        return fragmentPresenterGraphs.get(id);
    }

    public void clearPresenterGraphForFragment(String id) {
        fragmentPresenterGraphs.remove(id);
    }

    public void setPresenterGraphForFragment(String id, ObjectGraph presenterGraph) {
        fragmentPresenterGraphs.put(id, presenterGraph);
    }

    public void onFragmentDestroy(String fragmentUUID) {
        clearPresenterGraphForFragment(fragmentUUID);
    }

    public ObjectGraph getObjectGraph(BaseFragment fragment, String fragmentUUID) {
        ObjectGraph objectGraph = getPresenterGraphForFragment(fragmentUUID);
        if (objectGraph == null) {
            objectGraph = VideoApp.getApp(getApplicationContext()).getObjectGraph().plus(fragment.getPresenterModule());
            setPresenterGraphForFragment(fragmentUUID, objectGraph);
        }

        return objectGraph;
    }
}
