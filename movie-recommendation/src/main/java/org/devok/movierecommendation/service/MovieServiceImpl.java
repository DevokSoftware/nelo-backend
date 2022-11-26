package org.devok.movierecommendation.service;

import org.devok.movierecommendation.config.ConfigProperties;
import org.devok.movierecommendation.external.movieapi.model.MovieResponse;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.devok.movierecommendation.mapper.MovieMapper;
import org.devok.movierecommendation.model.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApiService movieApiService;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<MovieDTO> getMovieTrends() {
        MovieResponse movieResponse = movieApiService.getMoviesTrends();
        return movieResponse.getResults()
                .stream()
                .map(x -> movieMapper.mapToMovieDTO(x))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getMovieRecommendation() {
        return getMovieTrends().stream().max(Comparator.comparing(MovieDTO::getVoteAverage)).orElse(null);
    }
}
