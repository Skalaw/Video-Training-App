package com.skala.videotrainingapp;

import com.skala.videotrainingapp.image.ImageModule;

import dagger.Module;

/**
 * @author Skala
 */
@Module(library = true, includes = {NetModule.class, ImageModule.class})
public class AppModule {
}
