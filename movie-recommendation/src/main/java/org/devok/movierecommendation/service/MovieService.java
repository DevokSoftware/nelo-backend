package org.devok.movierecommendation.service;


import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;

import java.util.List;


public interface MovieService {
    List<MovieDTO> getMovieTrends();
    RecommendationDTO getMovieRecommendation();
    List<MovieDTO> searchMovies(String title);
    void addWatchedMovie(Long movieId);
}
