package org.devok.movierecommendation.repository;

import org.devok.movierecommendation.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByExternalId(Long movieId);
    List<Movie> findByUserId(Long userId);
}
