package org.devok.movierecommendation.external.movieapi.service;

import org.devok.movierecommendation.config.ConfigProperties;
import org.devok.movierecommendation.external.movieapi.model.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieApiService {

    @Autowired
    private ConfigProperties configProperties;

    /*
    Valid Media Types
    - all	Include all movies, TV shows and people in the results as a global trending list.
    - movie	Show the trending movies in the results.
    - tv	Show the trending TV shows in the results.
    - person	Show the trending people in the results.

    Valid Time Windows
    - day	View the trending list for the day.
    - week	View the trending list for the week.
     */
    public MovieResponse getMoviesTrends() {
        String uri = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + configProperties.getTmdbApiKey();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, MovieResponse.class);
    }
}
