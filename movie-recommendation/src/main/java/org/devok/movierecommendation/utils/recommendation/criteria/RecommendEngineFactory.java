package org.devok.movierecommendation.utils.recommendation.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecommendEngineFactory {
    @Autowired
    private List<RecommendEngine> recommendEngines;

    private static final Map<CriteriaEnum, RecommendEngine> myServiceCache = new HashMap<>();
    @PostConstruct
    public void initMyServiceCache() {
        for(RecommendEngine engine : recommendEngines) {
            myServiceCache.put(engine.getCriteriaType(), engine);
        }
    }
    public RecommendEngine getEngine(CriteriaEnum criteriaEnum) {
        log.info("Recommendation Engine: " + criteriaEnum);
        if (criteriaEnum == CriteriaEnum.GENRE) {
            return myServiceCache.get(CriteriaEnum.GENRE);
        }
        if (criteriaEnum == CriteriaEnum.CAST) {
            return myServiceCache.get(CriteriaEnum.CAST);
        }
        if (criteriaEnum == CriteriaEnum.DIRECTOR) {
            return myServiceCache.get(CriteriaEnum.DIRECTOR);
        }
        if (criteriaEnum == CriteriaEnum.RELEASE_DATE) {
            return myServiceCache.get(CriteriaEnum.RELEASE_DATE);
        }
        if (criteriaEnum == CriteriaEnum.TRENDING) {
            return myServiceCache.get(CriteriaEnum.TRENDING);
        }
        throw new IllegalArgumentException();
    }
}