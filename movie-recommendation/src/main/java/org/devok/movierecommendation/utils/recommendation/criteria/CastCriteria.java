package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.Person;

import java.util.List;
import java.util.Optional;

public class CastCriteria extends RecommendEngine {

    @Override
    public MovieDTO recommend(List<Movie> watchedMovies) {
        Movie randomMovie = getRandomWatchedMovie(watchedMovies);
        int randomNumber = rand.nextInt(randomMovie.getCast().size());
        Optional<Person> actor = randomMovie.getCast().stream().skip(randomNumber).findFirst();
        if (actor.isPresent()) {
            PersonMovies actorMovies = movieApiService.discoverMovieByPerson(actor.get().getExternalId());
            return getMovieFromResults(actorMovies.getMovies());
        }
        return null;
    }
}