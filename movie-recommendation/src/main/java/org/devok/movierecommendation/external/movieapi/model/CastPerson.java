package org.devok.movierecommendation.external.movieapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CastPerson extends Person{
    private Integer order;
}
