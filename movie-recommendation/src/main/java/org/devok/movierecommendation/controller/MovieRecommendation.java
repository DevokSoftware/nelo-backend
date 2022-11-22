package org.devok.movierecommendation.controller;

import org.devok.movierecommendation.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieRecommendation {

    @Autowired
    private ConfigProperties configProperties;

    @GetMapping("/movie")
    public ResponseEntity<?> getMovies() {
        try {
            String uri="https://api.themoviedb.org/3/trending/all/day?api_key=" + configProperties.getTmdbApiKey();
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


