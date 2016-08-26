package com.skala.core.api.repository;

import com.skala.core.api.genre.Genres;

import rx.Observable;

/**
 * @author Skała
 */
public interface GenreRepository {
    Observable<Genres> getMovieGenres();
}
