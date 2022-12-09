package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.model.Movie;

import java.util.List;

public class ReleaseDateCriteria extends RecommendEngine {

    @Override
    public MovieDTO recommend(List<Movie> watchedMovies) {
        Movie randomMovie = getRandomWatchedMovie(watchedMovies);
        MovieResults movieResults = movieApiService.discoverMovieByReleaseYear(randomMovie.getReleaseDate().getYear());
        return getMovieFromResults(movieResults.getMovies());
    }
}
