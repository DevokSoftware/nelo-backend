package org.devok.movierecommendation.utils.recommendation.criteria;

import org.devok.movierecommendation.dto.MovieDTO;
import org.devok.movierecommendation.external.movieapi.mapper.MovieApiMapper;
import org.devok.movierecommendation.external.movieapi.model.CastPerson;
import org.devok.movierecommendation.external.movieapi.model.MovieCredits;
import org.devok.movierecommendation.external.movieapi.model.PersonMovies;
import org.devok.movierecommendation.external.movieapi.service.MovieApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CastCriteria extends RecommendEngine {
    @Autowired
    public CastCriteria(MovieApiService movieApiService, MovieApiMapper movieApiMapper) {
        super(movieApiService, movieApiMapper);
    }

    @Override
    public CriteriaEnum getCriteriaType() {
        return CriteriaEnum.CAST;
    }

    @Override
    public MovieDTO recommend(Long watchedMovieId) {
        MovieCredits watchedMovieCredits = movieApiService.getMovieCredits(watchedMovieId);
        Optional<CastPerson> actor = getRandomActorFromWatchedMovie(watchedMovieCredits.getCast());
        if (actor.isPresent()) {
            PersonMovies actorMovies = movieApiService.discoverMovieByPerson(actor.get().getId());
            return getMovieFromResults(actorMovies.getMovies());
        }
        return null;
    }

    private Optional<CastPerson> getRandomActorFromWatchedMovie(List<CastPerson> cast) {
        List<CastPerson> filteredCast = cast.stream().filter(c -> c.getDepartment() != null && c.getDepartment().equals("Acting") && c.getOrder() < 3).collect(Collectors.toList());
        return filteredCast.stream().skip(rand.nextInt(filteredCast.size())).findFirst();
    }
}