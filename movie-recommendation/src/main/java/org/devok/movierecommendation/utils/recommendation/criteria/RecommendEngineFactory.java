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

    private static final Map<Criteria, RecommendEngine> myServiceCache = new HashMap<>();
    @PostConstruct
    public void initMyServiceCache() {
        for(RecommendEngine engine : recommendEngines) {
            myServiceCache.put(engine.getCriteriaType(), engine);
        }
    }
    public RecommendEngine getEngine(Criteria criteria) {
        log.info("Recommendation Engine: " + criteria);
        if (criteria == Criteria.GENRE) {
            return myServiceCache.get(Criteria.GENRE);
        }
        if (criteria == Criteria.CAST) {
            return myServiceCache.get(Criteria.CAST);
        }
        if (criteria == Criteria.DIRECTOR) {
            return myServiceCache.get(Criteria.DIRECTOR);
        }
        if (criteria == Criteria.RELEASE_DATE) {
            return myServiceCache.get(Criteria.RELEASE_DATE);
        }
        return null;
    }
}