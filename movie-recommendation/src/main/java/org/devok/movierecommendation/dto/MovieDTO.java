package org.devok.movierecommendation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String id;
    private String externalId;
    private String title;
    private String originalTitle;
    private Integer[] genreIds;
    private String overview;
    private Double voteAverage;
    private Integer releaseYear;
}
