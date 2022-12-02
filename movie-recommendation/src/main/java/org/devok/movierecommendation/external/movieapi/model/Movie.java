package org.devok.movierecommendation.external.movieapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.devok.movierecommendation.utils.OffsetDateTimeDeserializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

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
    private Integer popularity;
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonProperty("release_date")
    private OffsetDateTime releaseDate;
}
