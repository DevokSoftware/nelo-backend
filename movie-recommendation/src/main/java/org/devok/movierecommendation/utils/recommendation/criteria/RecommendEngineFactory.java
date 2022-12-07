package org.devok.movierecommendation.utils.recommendation.criteria;

import org.springframework.stereotype.Component;

@Component
public class RecommendEngineFactory {

    public RecommendEngine getEngine(Criteria criteria) {
        if (criteria == Criteria.GENRE) {
            return new GenreCriteria();
        } else if (criteria == Criteria.CAST) {
            return new CastCriteria();
        } else if (criteria == Criteria.DIRECTOR) {
            return new DirectorCriteria();
        } else if (criteria == Criteria.RELEASE_DATE) {
            return new ReleaseDateCriteria();
        }
        return null;
    }
}