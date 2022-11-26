package org.devok.movierecommendation.mapper;

import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.external.movieapi.model.MovieResponse;
import org.devok.movierecommendation.model.MovieDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDTO mapToMovieDTO(Movie movie);
}
