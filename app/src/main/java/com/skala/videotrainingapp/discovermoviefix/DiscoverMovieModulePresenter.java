package com.skala.videotrainingapp.discovermoviefix;

import com.skala.videotrainingapp.VideoApp;

import dagger.Module;

/**
 * @author Skala
 */
@Module(injects = {DiscoverMovieActivity.class}, addsTo = VideoApp.AppModule.class)
public class DiscoverMovieModulePresenter {
}
