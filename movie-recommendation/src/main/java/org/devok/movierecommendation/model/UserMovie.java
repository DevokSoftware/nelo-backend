package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_MOVIES")
@Getter
@Setter
public class UserMovie {
    @EmbeddedId
    private UserMovieId id;
    private MovieStatus status;

    public UserMovie(UserMovieId id, MovieStatus status) {
        this.id = id;
        this.status = status;
    }

    public UserMovie() {

    }
}
