package org.devok.movierecommendation.external.movieapi.mapper;

import org.devok.movierecommendation.external.movieapi.model.Movie;
import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.model.MovieGenre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieApiMapper {
    @Mapping(target = "externalId", source = "id")
    //@Mapping(target = "genreIds", expression = "java(mapGenres(movie.getGenres()))")
    @Mapping(target = "genreIds", source = "genres")
    @Mapping(target = "releaseYear", expression = "java(getYearFromDate(movie.getReleaseDate()))")
    MovieDTO mapToMovieDTO(Movie movie);

    List<MovieDTO> mapToMovieDTOList(List<Movie> moviesResults);

    default Integer[] mapGenres(List<MovieGenre> genres) {
        if (genres == null) {
            return new Integer[0];
        }
        Integer[] result = new Integer[genres.size()];
        for (int i = 0; i < genres.size(); i++) {
            result[i] = genres.get(i).getId();
        }
        return result;
    }

    default Integer getYearFromDate(OffsetDateTime date) {
        if (date == null) {
            return null;
        }
        return date.getYear();
    }
}
