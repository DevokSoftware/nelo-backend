package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.model.Movie;

public class DirectorCriteria implements RecommendEngine {

    @Override
    public Movie recommend() {
        System.out.println("director");
        return null;
    }
}
