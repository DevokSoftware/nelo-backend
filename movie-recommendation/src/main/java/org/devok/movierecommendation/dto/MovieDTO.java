package org.devok.movierecommendation.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private String externalId;
    private String title;
    private String originalTitle;
    private List<String> genres;
    private String overview;
    private Double voteAverage;
    private Integer releaseYear;
    private String imageUrl;
}
