package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrendingCriteria extends RecommendEngine {
    @Autowired
    public TrendingCriteria(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieApiMapper);
    }

    @Override
    public CriteriaEnum getCriteriaType() {
        return CriteriaEnum.TRENDING;
    }

    @Override
    public MovieDTO recommend(Long watchedMovieId) {
        MovieResults movieResults = movieApiService.getMoviesTrends();
        return getMovieFromResults(movieResults.getMovies());
    }
}
