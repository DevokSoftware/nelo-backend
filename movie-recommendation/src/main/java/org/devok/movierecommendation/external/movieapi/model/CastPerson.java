package org.devok.movierecommendation.external.movieapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastPerson {
    private Long id;
    @JsonProperty("known_for_department")
    private String department;
    private String name;
    private Integer order;
    private String job;
}
