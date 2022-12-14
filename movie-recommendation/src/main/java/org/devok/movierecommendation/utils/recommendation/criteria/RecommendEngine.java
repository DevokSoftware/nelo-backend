package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.repository.MovieRepository;

import java.util.List;
import java.util.Random;

public abstract class RecommendEngine {
    protected final MovieApiService movieApiService;
    private final MovieRepository movieRepository;
    private final MovieApiMapper movieApiMapper;

    protected Random rand;

    public RecommendEngine(MovieApiService movieApiService, MovieRepository movieRepository, MovieApiMapper movieApiMapper) {
        this.movieApiService = movieApiService;
        this.movieRepository = movieRepository;
        this.movieApiMapper = movieApiMapper;
        this.rand = new Random();
    }

    //TODO Search if it's better to use an optional
    public abstract MovieDTO recommend(List<UserMovie> watchedMovies);
    public abstract Criteria getCriteriaType();

    protected Movie getRandomWatchedMovie(List<UserMovie> watchedMovies) {
        UserMovie randomMovie = watchedMovies.get(rand.nextInt(watchedMovies.size()));
        return movieRepository.getReferenceById(randomMovie.getId().getMovieId());
    }

    protected MovieDTO getMovieFromResults(List<org.devok.movierecommendation.external.movieapi.model.Movie> movies) {
        List<MovieDTO> foundMovies = movieApiMapper.mapToMovieDTOList(movies);
        if (!foundMovies.isEmpty()) {
            //TODO maybe choose the movie using some criteria, like rating, popularity or something
            return foundMovies.get(rand.nextInt(foundMovies.size()));
        }
        return null;
    }
}