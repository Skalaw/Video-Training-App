package com.rocksolidapps.movies;

import com.rocksolidapps.movies.image.ImageModule;

import dagger.Module;

/**
 * @author Skala
 */
@Module(library = true, includes = {TheMovieDbModule.class, YoutubeModule.class, ImageModule.class})
public class AppModule {
}
