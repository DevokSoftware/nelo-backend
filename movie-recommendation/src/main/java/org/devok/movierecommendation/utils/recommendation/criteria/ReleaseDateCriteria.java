package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ReleaseDateCriteria extends RecommendEngine {
    @Autowired
    public ReleaseDateCriteria(MovieApiService movieApiService, MovieRepository movieRepository, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieRepository, movieApiMapper);
    }
    @Override
    public Criteria getCriteriaType() {
        return Criteria.RELEASE_DATE;
    }
    @Override
    public MovieDTO recommend(List<UserMovie> watchedMovies) {
        Movie randomMovie = getRandomWatchedMovie(watchedMovies);
        MovieResults movieResults = movieApiService.discoverMovieByReleaseYear(randomMovie.getReleaseDate().getYear());
        return getMovieFromResults(movieResults.getMovies());
    }
}
