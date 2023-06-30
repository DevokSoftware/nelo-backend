package org.devok.movierecommendation.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.dto.RecommendationDTO;
import org.devok.movierecommendation.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/trends")
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return new ResponseEntity<>(movieService.getMovieTrends(), HttpStatus.OK);
    }

    @GetMapping("/recommendation")
    public ResponseEntity<RecommendationDTO> getMovieRecommendation(@RequestParam(value = "watchedMovieId", required = false) Long watchedMovieId) {
        return new ResponseEntity<>(movieService.getMovieRecommendation(watchedMovieId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchMovies(@RequestParam("title") String title) {
        return new ResponseEntity<>(movieService.searchMovies(title), HttpStatus.OK);
    }
}


