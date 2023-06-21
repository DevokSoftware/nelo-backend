package org.devok.movierecommendation.service;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.utils.recommendation.MovieRecommendationEngine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieApiService movieApiService;
    private final MovieApiMapper movieApiMapper;
    private final MovieRecommendationEngine movieRecommendationEngine;

    public MovieServiceImpl(MovieApiService movieApiService, MovieApiMapper movieApiMapper, MovieRecommendationEngine movieRecommendationEngine) {
        this.movieApiService = movieApiService;
        this.movieApiMapper = movieApiMapper;
        this.movieRecommendationEngine = movieRecommendationEngine;
    }

    @Override
    public List<MovieDTO> getMovieTrends() {
        return movieApiMapper.mapToMovieDTOList(movieApiService.getMoviesTrends().getMovies());
    }

    @Override
    public RecommendationDTO getMovieRecommendation(Long watchedMovieId) {
        return movieRecommendationEngine.getRecommendation(watchedMovieId);
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        return movieApiMapper.mapToMovieDTOList(movieApiService.searchMovies(title).getMovies());
    }
}
