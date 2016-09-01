package com.rocksolidapps.core.api;

import com.rocksolidapps.core.api.model.YoutubeVideoInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Skala
 */
public interface YoutubeRestRepository {
    String QUERY_VIDEO_URL = "url";

    @GET("oembed?format=json")
    Observable<YoutubeVideoInfo> getYoutubeVideoInfo(@Query(QUERY_VIDEO_URL) String videoUrl);
}
