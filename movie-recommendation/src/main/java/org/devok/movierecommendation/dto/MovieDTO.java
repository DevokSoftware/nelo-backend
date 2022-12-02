package org.devok.movierecommendation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

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
    private OffsetDateTime releaseDate;
}
