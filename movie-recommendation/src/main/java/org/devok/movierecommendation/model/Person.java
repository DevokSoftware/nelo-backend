package org.devok.movierecommendation.model;

import javax.persistence.*;

@Entity
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
