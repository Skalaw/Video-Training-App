package com.skala.core.api;

import com.skala.core.api.model.YoutubeVideoInfo;
import com.skala.core.api.repository.YoutubeRepository;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author Skala
 */
@Singleton
public class YoutubeServiceApi implements YoutubeRepository {
    private final YoutubeRestRepository youtubeRestRepository;

    public YoutubeServiceApi(Retrofit retrofit) {
        youtubeRestRepository = retrofit.create(YoutubeRestRepository.class);
    }

    @Override
    public Observable<YoutubeVideoInfo> getYoutubeVideoInfo(String videoUrl) {
        return youtubeRestRepository.getYoutubeVideoInfo(videoUrl);
    }
}
