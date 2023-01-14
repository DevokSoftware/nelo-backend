package org.devok.movierecommendation.controller;

import lombok.extern.slf4j.Slf4j;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/trends")
    public ResponseEntity<?> getMovies() {
        return new ResponseEntity<>(movieService.getMovieTrends(), HttpStatus.OK);
    }

    @GetMapping("/recommendation")
    public ResponseEntity<?> getMovieRecommendation() {
        return new ResponseEntity<>(movieService.getMovieRecommendation(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchMovie(@RequestParam("title") String title) {
        return new ResponseEntity<>(movieService.searchMovies(title), HttpStatus.OK);
    }

    @PostMapping("/watched")
    public ResponseEntity<?> addWatchedMovie(@RequestBody MovieDTO movie) {
        movieService.addWatchedMovie(Long.parseLong(movie.getExternalId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


