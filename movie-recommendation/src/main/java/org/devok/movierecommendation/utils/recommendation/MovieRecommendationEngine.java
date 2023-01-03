package org.devok.movierecommendation.utils.recommendation;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.UserMovieRepository;
import org.devok.movierecommendation.utils.recommendation.criteria.Criteria;
import org.devok.movierecommendation.utils.recommendation.criteria.RecommendEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRecommendationEngine {
    private final UserMovieRepository userMovieRepository;
    private final RecommendEngineFactory recommendEngineFactory;

    @Autowired
    public MovieRecommendationEngine(UserMovieRepository userMovieRepository, RecommendEngineFactory recommendEngineFactory) {
        this.userMovieRepository = userMovieRepository;
        this.recommendEngineFactory = recommendEngineFactory;
    }

    public MovieDTO getRecommendation() {
        List<UserMovie> watchedMovies = userMovieRepository.findByIdUserId(1L);
        return recommendEngineFactory.getEngine(Criteria.randomType()).recommend(watchedMovies);
    }
}
