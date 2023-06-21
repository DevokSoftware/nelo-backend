package org.devok.movierecommendation.service;


import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;

import java.util.List;


public interface MovieService {
    List<MovieDTO> getMovieTrends();
    RecommendationDTO getMovieRecommendation(Long watchedMovieId);
    List<MovieDTO> searchMovies(String title);
}
