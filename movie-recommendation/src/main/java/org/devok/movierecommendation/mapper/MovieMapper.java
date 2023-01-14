package org.devok.movierecommendation.mapper;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.MovieDetails;
import org.devok.movierecommendation.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "id", expression = "java(null)")
    Movie mapToMovie(MovieDetails movieDetails);
    MovieDTO mapToMovieDTO(Movie movie);
}
