package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
    private Long id;
    private String name;
    private String genre;
}
