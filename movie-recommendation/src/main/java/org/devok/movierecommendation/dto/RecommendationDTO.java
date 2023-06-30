package org.devok.movierecommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecommendationDTO {
    private MovieDTO movie;
    private String criteria;
}
