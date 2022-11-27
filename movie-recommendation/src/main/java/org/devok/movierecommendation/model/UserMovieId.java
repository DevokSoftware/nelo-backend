package org.devok.movierecommendation.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserMovieId implements Serializable {
    private Long userId;
    private Long movieId;

    public UserMovieId() {
    }

    public UserMovieId(Long userId, Long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
