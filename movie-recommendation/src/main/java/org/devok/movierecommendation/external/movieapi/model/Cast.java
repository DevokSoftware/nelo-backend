package org.devok.movierecommendation.external.movieapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cast {
    private List<CastPerson> cast;
}
