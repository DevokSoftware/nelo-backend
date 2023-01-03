package org.devok.movierecommendation.service;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.CrewPerson;
import org.devok.movierecommendation.external.movieapi.model.MovieCredits;
import org.devok.movierecommendation.external.movieapi.model.CastPerson;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.mapper.MovieMapper;
import org.devok.movierecommendation.model.*;
import org.devok.movierecommendation.repository.MovieRepository;
import org.devok.movierecommendation.repository.UserMovieRepository;
import org.devok.movierecommendation.utils.recommendation.MovieRecommendationEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        return movieApiMapper.mapToMovieDTOList(movieApiService.getMoviesTrends().getMovies());
    }

    @Override
    public MovieDTO getMovieRecommendation() {
        return movieRecommendationEngine.getRecommendation();
    }

    @Override
    public List<MovieDTO> searchMovies(String title) {
        return movieApiMapper.mapToMovieDTOList(movieApiService.searchMovies(title).getMovies());
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
        MovieCredits movieCredits = movieApiService.getMovieCredits(movieExternalId);
        movie.setDirector(fetchDirector(movieCredits.getCrew()));
        movie.setCast(fetchActors(movieCredits.getCast()));
        movie.setGenres(fetchGenres(movieDTO.getGenreIds()));
        movieRepository.save(movie);
        return movie;
    }

    private Person fetchDirector(List<CrewPerson> crew) {
        Optional<CrewPerson> cp = crew.stream().filter(c -> c.getJob() != null && c.getJob().equals("Director")).findFirst();
        return cp.map(crewPerson -> new Person(crewPerson.getId(), crewPerson.getName())).orElse(null);
    }

    private List<Person> fetchActors(List<CastPerson> cast) {
        List<Person> result = new ArrayList<>();
        List<CastPerson> castList = cast.stream().filter(c -> c.getDepartment() != null && c.getDepartment().equals("Acting") && c.getOrder() < 6).collect(Collectors.toList());
        for (CastPerson cp : castList) {
            result.add(new Person(cp.getId(), cp.getName()));
        }
        return result;
    }

    private Set<Genre> fetchGenres(Integer[] genresIds) {
        Set<Genre> genres = new HashSet<>();
        for (int genreId : genresIds) {
            genres.add(Genre.getGenreById(genreId));
        }

        return genres;
    }
}
