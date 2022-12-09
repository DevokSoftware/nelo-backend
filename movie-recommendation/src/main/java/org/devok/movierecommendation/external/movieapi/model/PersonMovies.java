package org.devok.movierecommendation.external.movieapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonMovies {
    @JsonProperty("cast")
    private List<Movie> movies;
}
