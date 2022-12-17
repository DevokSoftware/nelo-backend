package org.devok.movierecommendation.utils.recommendation;

import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.UserMovieRepository;
import org.devok.movierecommendation.utils.recommendation.criteria.RecommendEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRecommendationEngine {
    private final MovieApiService movieApiService;
    private final UserMovieRepository userMovieRepository;
    private final RecommendEngineFactory recommendEngineFactory;

    @Autowired
    public MovieRecommendationEngine(MovieApiService movieApiService,UserMovieRepository userMovieRepository, RecommendEngineFactory recommendEngineFactory) {
        this.movieApiService = movieApiService;
        this.userMovieRepository = userMovieRepository;
        this.recommendEngineFactory = recommendEngineFactory;
    }

    public Movie getRecommendation() {
        List<UserMovie> watchedMovies = userMovieRepository.findByIdUserId(1L);
       //  recommendEngineFactory.getEngine(Criteria.randomType()).recommend(watchedMovies);
        return new Movie();
    }
}
