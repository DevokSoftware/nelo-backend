package org.devok.movierecommendation.utils.recommendation;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;
import org.devok.movierecommendation.utils.recommendation.criteria.CriteriaEnum;
import org.devok.movierecommendation.utils.recommendation.criteria.RecommendEngineFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRecommendationEngine {
    private final RecommendEngineFactory recommendEngineFactory;

    @Autowired
    public MovieRecommendationEngine(RecommendEngineFactory recommendEngineFactory) {
        this.recommendEngineFactory = recommendEngineFactory;
    }

    public RecommendationDTO getRecommendation(Long watchedMovieId) {
        CriteriaEnum criteriaEnum = watchedMovieId != null ? CriteriaEnum.randomType() : CriteriaEnum.TRENDING;
        MovieDTO movie = recommendEngineFactory.getEngine(criteriaEnum).recommend(watchedMovieId);
        return new RecommendationDTO(movie, criteriaEnum.getLabel());
    }
}
