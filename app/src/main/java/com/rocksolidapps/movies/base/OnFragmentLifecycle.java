package com.rocksolidapps.movies.base;

/**
 * @author Skala
 */
public interface OnFragmentLifecycle {
    void onFragmentCreate(BaseFragment baseFragment, String fragmentUUID);

    void onFragmentDestroy(String fragmentUUID);
}
