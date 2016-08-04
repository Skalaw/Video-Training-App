package com.skala.videotrainingapp;

/**
 * @author Skala
 */
public interface OnFragmentLifecycle {
    void onFragmentCreate(BaseFragment baseFragment, String fragmentUUID);
    void onFragmentDestroy(String fragmentUUID);
}
