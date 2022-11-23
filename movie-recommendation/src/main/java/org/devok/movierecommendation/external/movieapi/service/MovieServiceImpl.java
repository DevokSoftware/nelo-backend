package org.devok.movierecommendation.external.movieapi.service;

import org.devok.movierecommendation.config.ConfigProperties;
import org.devok.movierecommendation.external.movieapi.model.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ConfigProperties configProperties;

    @Override
    public MovieResponse getMovieTrends() {
        String uri = "https://api.themoviedb.org/3/trending/all/day?api_key=" + configProperties.getTmdbApiKey();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, MovieResponse.class);
    }
}
