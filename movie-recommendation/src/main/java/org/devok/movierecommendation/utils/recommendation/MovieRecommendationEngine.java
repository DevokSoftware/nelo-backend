package org.devok.movierecommendation.utils.recommendation;

import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.repository.MovieRepository;
import org.devok.movierecommendation.utils.recommendation.criteria.Criteria;
import org.devok.movierecommendation.utils.recommendation.criteria.RecommendEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRecommendationEngine {
    private final MovieApiService movieApiService;
    private final MovieRepository movieRepository;
    private final RecommendEngineFactory recommendEngineFactory;

    @Autowired
    public MovieRecommendationEngine(MovieApiService movieApiService, MovieRepository movieRepository, RecommendEngineFactory recommendEngineFactory) {
        this.movieApiService = movieApiService;
        this.movieRepository = movieRepository;
        this.recommendEngineFactory = recommendEngineFactory;
    }

    public Movie getRecommendation() {
        List<Movie> watchedMovies = movieRepository.findByUserId(1L);
        recommendEngineFactory.getEngine(Criteria.randomType()).recommend(watchedMovies);
        return new Movie();
    }
}
