package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PERSONS")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long externalId;
    private String name;

    public Person() {
    }

    public Person(Long externalId, String name) {
        this.externalId = externalId;
        this.name = name;
    }
}
