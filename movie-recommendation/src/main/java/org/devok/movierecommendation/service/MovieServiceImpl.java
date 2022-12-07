package org.devok.movierecommendation.service;

import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.Cast;
import org.devok.movierecommendation.external.movieapi.model.CastPerson;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.mapper.MovieMapper;
import org.devok.movierecommendation.model.*;
import org.devok.movierecommendation.repository.MovieRepository;
import org.devok.movierecommendation.repository.UserMovieRepository;
import org.devok.movierecommendation.utils.recommendation.MovieRecommendationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiService movieApiService;

    @Autowired
    private MovieApiMapper movieApiMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserMovieRepository userMovieRepository;

    @Autowired
    private MovieRecommendationEngine movieRecommendationEngine;

    @Override
    public List<MovieDTO> getMovieTrends() {
        return movieApiMapper.mapToMovieDTOList(movieApiService.getMoviesTrends().getResults());
    }

    @Override
    public MovieDTO getMovieRecommendation() {
        return movieMapper.mapToMovieDTO(movieRecommendationEngine.getRecommendation());
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        return movieApiMapper.mapToMovieDTOList(movieApiService.searchMovies(title).getResults());
    }

    @Override
    public void addWatchedMovie(Long movieExternalId) {
        Movie movie;
        movie = movieRepository.findByExternalId(movieExternalId);
        if (movie == null) {
            movie = createMovie(movieExternalId);
        }
        //TODO When users logic is implemented should not be used userId=1
        userMovieRepository.save(new UserMovie(new UserMovieId(1L, movie.getId()), MovieStatus.WATCHED));
    }

    private Movie createMovie(Long movieExternalId) {
        MovieDTO movieDTO = movieApiMapper.mapToMovieDTO(movieApiService.getMovieById(movieExternalId));
        Movie movie = movieMapper.mapToMovie(movieDTO);
        Cast cast = movieApiService.getMovieCast(movieExternalId);
        movie.setDirector(fetchDirector(cast));
        movie.setCast(fetchActors(cast));
        movieRepository.save(movie);
        return movie;
    }

    private Person fetchDirector(Cast cast) {
        Optional<CastPerson> cp = cast.getCast().stream().filter(c -> c.getJob().equals("Director")).findFirst();
        return cp.map(castPerson -> new Person(castPerson.getId(), castPerson.getName())).orElse(null);
    }

    private List<Person> fetchActors(Cast cast) {
        List<Person> result = new ArrayList<>();
        List<CastPerson> castList = cast.getCast().stream().filter(c -> c.getDepartment().equals("Acting") && c.getOrder() < 6).collect(Collectors.toList());
        for (CastPerson cp : castList) {
            result.add(new Person(cp.getId(), cp.getName()));
        }
        return result;
    }
}
