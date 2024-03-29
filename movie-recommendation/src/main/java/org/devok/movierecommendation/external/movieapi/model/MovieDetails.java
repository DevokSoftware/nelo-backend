package org.devok.movierecommendation.external.movieapi.model;

import java.time.OffsetDateTime;
import java.util.List;

import org.devok.movierecommendation.utils.OffsetDateTimeDeserializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetails {
    private String id;
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("genres")
    private List<MovieGenre> movieGenres;
    private String overview;
    @JsonProperty("vote_average")
    private Double voteAverage;
    private Integer popularity;
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @JsonProperty("release_date")
    private OffsetDateTime releaseDate;
}
