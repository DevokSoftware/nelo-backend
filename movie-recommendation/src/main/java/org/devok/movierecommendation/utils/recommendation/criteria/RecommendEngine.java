package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

public abstract class RecommendEngine {
    @Autowired
    protected MovieApiService movieApiService;
    @Autowired
    protected MovieRepository movieRepository;
    @Autowired
    protected MovieApiMapper movieApiMapper;
    protected Random rand;

    protected RecommendEngine() {
        this.rand = new Random();
    }

    //TODO Search if it better to use an optional
    public abstract MovieDTO recommend(List<Movie> watchedMovies);

    protected Movie getRandomWatchedMovie(List<Movie> watchedMovies) {
        return watchedMovies.get(rand.nextInt(watchedMovies.size()));
    }

    protected MovieDTO getMovieFromResults(List<org.devok.movierecommendation.external.movieapi.model.Movie> movies){
        List<MovieDTO> foundMovies = movieApiMapper.mapToMovieDTOList(movies);
        if (!foundMovies.isEmpty()) {
            //TODO maybe choose the movie using some criteria, like rating, popularity or something
            return foundMovies.get(0);
        }
        return null;
    }
}