package com.skala.core.ui.discovermovie;

import com.skala.core.api.genre.Genres;
import com.skala.core.api.repository.GenreRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author Skała
 */
@Singleton
public class GenreMoviesUseCase {
    private final GenreRepository genreRepository;

    @Inject
    public GenreMoviesUseCase(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Observable<Genres> loadGenreMovies() {
        return genreRepository.getMovieGenres();
    }
}
