package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.model.Movie;

public interface RecommendEngine {
    Movie recommend();
}