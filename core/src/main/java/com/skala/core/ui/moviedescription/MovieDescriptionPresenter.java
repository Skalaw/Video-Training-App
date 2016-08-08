package com.skala.core.ui.moviedescription;

import com.skala.core.api.repository.VideoRepository;
import com.skala.core.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author Skała
 */
public class MovieDescriptionPresenter extends BasePresenter<MovieDescriptionUi> {
    private final VideoRepository videoRepository;

    @Inject
    public MovieDescriptionPresenter(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }
}
