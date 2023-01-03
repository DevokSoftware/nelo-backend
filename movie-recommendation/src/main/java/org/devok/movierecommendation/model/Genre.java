package org.devok.movierecommendation.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Genre {
    ACTION(28),
    ADVENTURE(12),
    ANIMATION(16),
    COMEDY(35),
    CRIME(80),
    DOCUMENTARY(99),
    DRAMA(18),
    FAMILY(10751),
    FANTASY(14),
    HISTORY(36),
    HORROR(27),
    MUSIC(10402),
    MYSTERY(9648),
    ROMANCE(10749),
    SCIENCE_FICTION(878),
    TV_MOVIE(10770),
    THRILLER(53),
    WESTERN(37),
    WAR(10752);
    private final int id;

    private Genre(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private static final Map<Integer,Genre> ENUM_MAP;
    static {
        Map<Integer,Genre> map = new ConcurrentHashMap<>();
        for (Genre instance : Genre.values()) {
            map.put(instance.getId(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Genre getGenreById (int genreId) {
        return ENUM_MAP.get(genreId);
    }
}
