package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.MovieDetails;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReleaseDateCriteria extends RecommendEngine {
    @Autowired
    public ReleaseDateCriteria(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieApiMapper);
    }

    @Override
    public Criteria getCriteriaType() {
        return Criteria.RELEASE_DATE;
    }

    @Override
    public MovieDTO recommend(Long watchedMovieId) {
        MovieDetails watchedMovieDetails = movieApiService.getMovieById(watchedMovieId);

        MovieResults movieResults = movieApiService.discoverMovieByReleaseYear(watchedMovieDetails.getReleaseDate().getYear());
        return getMovieFromResults(movieResults.getMovies());
    }
}
