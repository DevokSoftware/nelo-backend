package org.devok.movierecommendation.utils.recommendation;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;
import org.devok.movierecommendation.utils.recommendation.criteria.Criteria;
import org.devok.movierecommendation.utils.recommendation.criteria.RecommendEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieRecommendationEngine {
    private final RecommendEngineFactory recommendEngineFactory;

    @Autowired
    public MovieRecommendationEngine(RecommendEngineFactory recommendEngineFactory) {
        this.recommendEngineFactory = recommendEngineFactory;
    }

    public RecommendationDTO getRecommendation(Long watchedMovieId) {
        Criteria criteria = Criteria.randomType();
        MovieDTO movie = recommendEngineFactory.getEngine(criteria).recommend(watchedMovieId);
        return new RecommendationDTO(movie, criteria);
    }
}
