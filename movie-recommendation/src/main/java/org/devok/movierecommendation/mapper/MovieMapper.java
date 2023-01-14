package org.devok.movierecommendation.mapper;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.MovieDetails;
import org.devok.movierecommendation.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie mapToMovie(MovieDetails movieDetails);
    MovieDTO mapToMovieDTO(Movie movie);
}
