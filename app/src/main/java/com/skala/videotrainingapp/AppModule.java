package com.skala.videotrainingapp;

import com.skala.videotrainingapp.image.ImageModule;

import dagger.Module;

/**
 * @author Skala
 */
@Module(library = true, includes = {TheMovieDbModule.class, YoutubeModule.class, ImageModule.class})
public class AppModule {
}
