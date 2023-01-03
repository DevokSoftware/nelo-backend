package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.Person;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CastCriteria extends RecommendEngine {
    @Autowired
    public CastCriteria(MovieApiService movieApiService, MovieRepository movieRepository, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieRepository, movieApiMapper);
    }

    @Override
    public Criteria getCriteriaType() {
        return Criteria.CAST;
    }

    @Override
    public MovieDTO recommend(List<UserMovie> watchedMovies) {
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