package org.devok.movierecommendation.mapper;

import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.dto.MovieDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDTO mapToMovieDTO(Movie movie);
}
