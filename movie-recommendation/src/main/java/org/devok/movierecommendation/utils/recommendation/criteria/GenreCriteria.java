package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Genre;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenreCriteria extends RecommendEngine {
    @Autowired
    public GenreCriteria(MovieApiService movieApiService, MovieRepository movieRepository, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieRepository, movieApiMapper);
    }

    @Override
    public Criteria getCriteriaType() {
        return Criteria.GENRE;
    }
    @Override
    public MovieDTO recommend(List<UserMovie> watchedMovies) {
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

