package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.model.Genre;
import org.devok.movierecommendation.model.Movie;

import java.util.List;
import java.util.Optional;


public class GenreCriteria extends RecommendEngine {

    @Override
    public MovieDTO recommend(List<Movie> watchedMovies) {
        Movie randomMovie = getRandomWatchedMovie(watchedMovies);
        int randomNumber = rand.nextInt(randomMovie.getGenres().size());
        Optional<Genre> movieGenre = randomMovie.getGenres().stream().skip(randomNumber).findFirst();
        if (movieGenre.isPresent()) {
            MovieResults movieResults = movieApiService.discoverMovieByGenre(movieGenre.get().getId());
            return getMovieFromResults(movieResults.getMovies());
        }
        return null;
    }
}

