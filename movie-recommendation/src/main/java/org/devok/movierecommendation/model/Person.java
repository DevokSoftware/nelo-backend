package org.devok.movierecommendation.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONS")
public class Person {
    @Id
    private Long id;
}
