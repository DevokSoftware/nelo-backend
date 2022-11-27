package org.devok.movierecommendation.service;

import org.devok.movierecommendation.external.movieapi.model.MovieResponse;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.mapper.MovieMapper;
import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.MovieStatus;
import org.devok.movierecommendation.model.UserMovie;
import org.devok.movierecommendation.model.UserMovieId;
import org.devok.movierecommendation.repository.MovieRepository;
import org.devok.movierecommendation.repository.UserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiService movieApiService;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Override
    public List<MovieDTO> getMovieTrends() {
        return transformMoviesList(movieApiService.getMoviesTrends());
    }

    @Override
    public MovieDTO getMovieRecommendation() {
        return getMovieTrends().stream().max(Comparator.comparing(MovieDTO::getVoteAverage)).orElse(null);
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        return transformMoviesList(movieApiService.searchMovies(title));
    }

    @Override
    public void addWatchedMovie(Long movieExternalId) {
        Movie movie;
        movie = movieRepository.findByExternalId(movieExternalId);
        if (movie == null) {
            movie = new Movie(movieExternalId);
        }
        //TODO When users logic is implemented should not be used userId=1
        userMovieRepository.save(new UserMovie(new UserMovieId(1L, movie.getId()), MovieStatus.WATCHED));
    }

    private List<MovieDTO> transformMoviesList(MovieResponse movieResponse) {
        return movieResponse.getResults()
                .stream()
                .map(x -> movieMapper.mapToMovieDTO(x))
                .collect(Collectors.toList());
    }
}
