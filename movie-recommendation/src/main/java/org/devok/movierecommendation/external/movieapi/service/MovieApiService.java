package org.devok.movierecommendation.external.movieapi.service;

import org.devok.movierecommendation.config.ConfigProperties;
import org.devok.movierecommendation.external.movieapi.model.Cast;
import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieApiService {

    private static final String API_URL = "https://api.themoviedb.org/3";
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
    public MovieResults getMoviesTrends() {
        String uri = API_URL + "/trending/movie/week?api_key=" + configProperties.getTmdbApiKey();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public MovieResults searchMovies(String title) {
        String uri = API_URL + "/search/movie?api_key=" + configProperties.getTmdbApiKey() + "&query=" + title;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public Cast getMovieCast(Long movieId) {
        String uri = API_URL + "/movie/" + movieId + "/credits?api_key=" + configProperties.getTmdbApiKey();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Cast.class);
    }
    public Movie getMovieById(Long movieId) {
        String uri = API_URL + "/movie/" + movieId + "?api_key=" + configProperties.getTmdbApiKey();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Movie.class);
    }
}
