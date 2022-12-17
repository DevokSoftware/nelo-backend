package org.devok.movierecommendation.repository;

import org.devok.movierecommendation.model.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
    List<UserMovie> findByIdUserId(Long userId);
}
