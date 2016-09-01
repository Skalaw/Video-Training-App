package com.rocksolidapps.core.api.repository;

import com.rocksolidapps.core.api.genre.Genres;

import rx.Observable;

/**
 * @author Skała
 */
public interface GenreRepository {
    Observable<Genres> getMovieGenres();
}
