package org.devok.movierecommendation.service;


import org.devok.movierecommendation.model.MovieDTO;

import java.util.List;


public interface MovieService {
    List<MovieDTO> getMovieTrends();
    MovieDTO getMovieRecommendation();
}
