package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.devok.movierecommendation.model.Movie;

import java.util.List;

public class DirectorCriteria extends RecommendEngine {

    @Override
    public MovieDTO recommend(List<Movie> watchedMovies) {
        Movie randomMovie = getRandomWatchedMovie(watchedMovies);
        PersonMovies directorMovies = movieApiService.discoverMovieByPerson(randomMovie.getDirector().getExternalId());
        return getMovieFromResults(directorMovies.getMovies());
    }
}
