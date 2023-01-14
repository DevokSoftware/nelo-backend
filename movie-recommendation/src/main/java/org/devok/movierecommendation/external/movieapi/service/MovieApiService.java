package org.devok.movierecommendation.external.movieapi.service;

import org.devok.movierecommendation.config.ConfigProperties;
import org.devok.movierecommendation.external.movieapi.model.MovieCredits;
import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.external.movieapi.model.MovieDetails;
import org.devok.movierecommendation.external.movieapi.model.MovieResults;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieApiService {

    private static final String API_URL = "https://api.themoviedb.org/3";
    @Autowired
    private ConfigProperties configProperties;

    private RestTemplate restTemplate;

    public MovieApiService() {
        restTemplate = new RestTemplate();
    }

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
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public MovieResults searchMovies(String title) {
        String uri = API_URL + "/search/movie?api_key=" + configProperties.getTmdbApiKey() + "&query=" + title;
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public MovieCredits getMovieCredits(Long movieId) {
        String uri = API_URL + "/movie/" + movieId + "/credits?api_key=" + configProperties.getTmdbApiKey();
        return restTemplate.getForObject(uri, MovieCredits.class);
    }

    public MovieDetails getMovieById(Long movieId) {
        String uri = API_URL + "/movie/" + movieId + "?api_key=" + configProperties.getTmdbApiKey();
        return restTemplate.getForObject(uri, MovieDetails.class);
    }

    public MovieResults discoverMovieByGenre(Integer genreId) {
        String uri = API_URL + "/discover/movie?api_key=" + configProperties.getTmdbApiKey() + "&with_genres=" + genreId;
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public MovieResults discoverMovieByReleaseYear(Integer year) {
        String uri = API_URL + "/discover/movie?api_key=" + configProperties.getTmdbApiKey() + "&primary_release_year=" + year;
        return restTemplate.getForObject(uri, MovieResults.class);
    }

    public PersonMovies discoverMovieByPerson(Long personId) {
        String uri = API_URL + "/person/" + personId + "/movie_credits?api_key=" + configProperties.getTmdbApiKey();
        return restTemplate.getForObject(uri, PersonMovies.class);
    }
}
