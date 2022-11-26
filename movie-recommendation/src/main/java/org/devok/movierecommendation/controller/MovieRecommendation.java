package org.devok.movierecommendation.controller;

import org.devok.movierecommendation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendation {

    @Autowired
    private MovieService movieService;

    @GetMapping("/trends")
    public ResponseEntity<?> getMovies() {
        try {
            return new ResponseEntity<>(movieService.getMovieTrends(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recommendation")
    public ResponseEntity<?> getMovieRecommendation() {
        try {
            return new ResponseEntity<>(movieService.getMovieRecommendation(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


