package org.devok.movierecommendation.external.movieapi.mapper;

import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.model.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieApiMapper {
    @Mapping(target = "externalId", source = "id")
    @Mapping(target = "genres", expression = "java(translateGenres(movie.getGenres()))")
    @Mapping(target = "releaseYear", expression = "java(getYearFromDate(movie.getReleaseDate()))")
    MovieDTO mapToMovieDTO(Movie movie);

    List<MovieDTO> mapToMovieDTOList(List<Movie> moviesResults);

    default List<String> translateGenres(int[] genres) {
        List<String> translatedGenres = new ArrayList<>();
        for (int genreId : genres) {
            translatedGenres.add(Genre.getGenreById(genreId).getLabel());
        }
        return translatedGenres;
    }

    default Integer getYearFromDate(OffsetDateTime date) {
        if (date == null) {
            return null;
        }
        return date.getYear();
    }
}
