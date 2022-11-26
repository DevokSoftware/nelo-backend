package org.devok.movierecommendation.external.movieapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("genre_ids")
    private Integer[] genreIds;
    private String overview;
    @JsonProperty("vote_average")
    private Double voteAverage;
}
