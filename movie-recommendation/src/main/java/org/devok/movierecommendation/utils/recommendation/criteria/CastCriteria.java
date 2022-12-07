package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.model.Movie;

public class CastCriteria implements RecommendEngine {

    @Override
    public Movie recommend() {
        System.out.println("cast");
        return null;
    }
}