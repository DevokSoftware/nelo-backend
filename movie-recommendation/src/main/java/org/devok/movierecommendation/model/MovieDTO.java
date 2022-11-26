package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String title;
    private String originalTitle;
    private Integer[] genreIds;
    private String overview;
    private Double voteAverage;
}
