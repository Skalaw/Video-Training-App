package com.skala.videotrainingapp.discovermovie;

import com.skala.videotrainingapp.NetModule;

import dagger.Module;

/**
 * @author Skala
 */
@Module(injects = {DiscoverMovieActivity.class}, addsTo = NetModule.class)
public class DiscoverMovieModulePresenter {
}
