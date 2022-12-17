package org.devok.movierecommendation.repository;

import org.devok.movierecommendation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByExternalId(Long movieId);
}
