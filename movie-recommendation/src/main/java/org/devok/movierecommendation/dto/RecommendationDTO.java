package org.devok.movierecommendation.dto;

import org.devok.movierecommendation.utils.recommendation.criteria.Criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecommendationDTO {
    private MovieDTO movie;
    //TODO create DTO for this
    private Criteria criteria;
}
