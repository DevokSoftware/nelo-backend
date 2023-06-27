package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;

import java.util.List;
import java.util.Random;

public abstract class RecommendEngine {
    protected final MovieApiService movieApiService;
    private final MovieApiMapper movieApiMapper;

    protected Random rand;

    protected RecommendEngine(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        this.movieApiService = movieApiService;
        this.movieApiMapper = movieApiMapper;
        this.rand = new Random();
    }

    //TODO Search if it's better to use an optional
    public abstract MovieDTO recommend(Long watchedMovieId);
    public abstract Criteria getCriteriaType();

    protected MovieDTO getMovieFromResults(List<Movie> movies) {
        List<MovieDTO> foundMovies = movieApiMapper.mapToMovieDTOList(movies);
        if (!foundMovies.isEmpty()) {
            //TODO maybe choose the movie using some criteria, like rating, popularity or something
            return foundMovies.get(rand.nextInt(foundMovies.size()));
        }
        return null;
    }
}