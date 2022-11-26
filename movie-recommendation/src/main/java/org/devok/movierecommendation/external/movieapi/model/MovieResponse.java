package org.devok.movierecommendation.external.movieapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieResponse {
    private List<Movie> results;
}

