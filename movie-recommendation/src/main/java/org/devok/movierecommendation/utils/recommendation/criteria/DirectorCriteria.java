package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.CrewPerson;
import org.devok.movierecommendation.external.movieapi.model.MovieCredits;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DirectorCriteria extends RecommendEngine {
    @Autowired
    public DirectorCriteria(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieApiMapper);
    }

    @Override
    public Criteria getCriteriaType() {
        return Criteria.DIRECTOR;
    }

    @Override
    public MovieDTO recommend(Long watchedMovieId) {
        MovieCredits watchedMovieCredits = movieApiService.getMovieCredits(watchedMovieId);
        Optional<CrewPerson> director = getDirectorFromWatchedMovie(watchedMovieCredits.getCrew());
        if (director.isPresent()) {
            PersonMovies directorMovies = movieApiService.discoverMovieByPerson(director.get().getId());
            return getMovieFromResults(directorMovies.getMovies());
        }
        return null;
    }

    private Optional<CrewPerson> getDirectorFromWatchedMovie(List<CrewPerson> crew) {
        List<CrewPerson> filteredCast = crew.stream().filter(c -> c.getJob() != null && c.getJob().equals("Director")).collect(Collectors.toList());
        return filteredCast.stream().skip(rand.nextInt(filteredCast.size())).findFirst();
    }

}
