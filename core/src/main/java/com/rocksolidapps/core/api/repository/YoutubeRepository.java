package com.rocksolidapps.core.api.repository;

import com.rocksolidapps.core.api.model.YoutubeVideoInfo;

import rx.Observable;

/**
 * @author Skala
 */
public interface YoutubeRepository {
    Observable<YoutubeVideoInfo> getYoutubeVideoInfo(String videoUrl);
}
