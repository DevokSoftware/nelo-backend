package org.devok.movierecommendation.external.movieapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieCredits {
    private List<CastPerson> cast;
    private List<CrewPerson> crew;
}
