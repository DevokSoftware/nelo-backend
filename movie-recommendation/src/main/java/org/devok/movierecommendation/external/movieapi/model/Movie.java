package org.devok.movierecommendation.external.movieapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.devok.movierecommendation.utils.OffsetDateTimeDeserializer;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Movie {
    private String id;
    private String title;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("genre_ids")
    private int[] genres;
    private String overview;
    @JsonProperty("vote_average")
    private Double voteAverage;
    private int popularity;
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @JsonProperty("release_date")
    private OffsetDateTime releaseDate;
    @JsonProperty("poster_path")
    private String imageUrl;
}
