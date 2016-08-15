package com.skala.core.api.repository;

import com.skala.core.api.model.YoutubeVideoInfo;

import rx.Observable;

/**
 * @author Skala
 */
public interface YoutubeRepository {
    Observable<YoutubeVideoInfo> getYoutubeVideoInfo(String videoUrl);
}
