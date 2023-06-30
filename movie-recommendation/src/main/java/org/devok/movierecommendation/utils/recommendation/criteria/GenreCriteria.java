package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.MovieDetails;
import org.devok.movierecommendation.external.movieapi.model.MovieGenre;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GenreCriteria extends RecommendEngine {
    @Autowired
    public GenreCriteria(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieApiMapper);
    }

    @Override
    public CriteriaEnum getCriteriaType() {
        return CriteriaEnum.GENRE;
    }

    @Override
    public MovieDTO recommend(Long watchedMovieId) {
        MovieDetails watchedMovieDetails = movieApiService.getMovieById(watchedMovieId);
        Optional<MovieGenre> movieGenre = getRandomGenreFromWatchedMovie(watchedMovieDetails.getMovieGenres());
        if (movieGenre.isPresent()) {
            MovieResults movieResults = movieApiService.discoverMovieByGenre(movieGenre.get().getId());
            return getMovieFromResults(movieResults.getMovies());
        }
        return null;
    }

    private Optional<MovieGenre> getRandomGenreFromWatchedMovie(List<MovieGenre> movieGenres) {
        return movieGenres.stream().skip(rand.nextInt(movieGenres.size())).findFirst();
    }
}

