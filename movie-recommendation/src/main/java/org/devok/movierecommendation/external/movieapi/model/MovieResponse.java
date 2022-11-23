package org.devok.movierecommendation.external.movieapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieResponse {
    private List<Movie> results;
}

@Getter
@Setter
class Movie {
    private String title;
    private String original_title;
    private Integer[] genre_ids;
    private String overview;
    private Double vote_average;
}
