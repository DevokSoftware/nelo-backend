package org.devok.movierecommendation.repository;

import org.devok.movierecommendation.model.Movie;
import org.devok.movierecommendation.model.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
}
