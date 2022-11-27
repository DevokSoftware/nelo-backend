package org.devok.movierecommendation.service;


import org.devok.movierecommendation.dto.MovieDTO;

import java.util.List;


public interface MovieService {
    List<MovieDTO> getMovieTrends();
    MovieDTO getMovieRecommendation();
    List<MovieDTO> searchMovies(String title);
    void addWatchedMovie(Long movieId);
}
