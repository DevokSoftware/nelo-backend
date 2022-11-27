package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/*
 TODO // Think about store information about the Movie (title, releaseDate, etc) in the database to avoid any loss of information
  in the future in case of External API change or a necessity to change API to other.
 */
@Entity
@Table(name = "MOVIES")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long externalId;

    public Movie() {
    }

    public Movie(Long externalId) {
        this.externalId = externalId;
    }
}
