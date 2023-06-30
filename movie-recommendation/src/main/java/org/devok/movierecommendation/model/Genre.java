package org.devok.movierecommendation.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Genre {
    ACTION(28, "Action"),
    ADVENTURE(12, "Adventure"),
    ANIMATION(16, "Animation"),
    COMEDY(35, "Comedy"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentary"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    FANTASY(14, "Fantasy"),
    HISTORY(36, "History"),
    HORROR(27, "Horror"),
    MUSIC(10402, "Music"),
    MYSTERY(9648, "Mystery"),
    ROMANCE(10749, "Romance"),
    SCIENCE_FICTION(878, "Science Fiction"),
    TV_MOVIE(10770, "TV Movie"),
    THRILLER(53, "Thriller"),
    WESTERN(37, "Western"),
    WAR(10752, "War");

    private final int id;
    private final String label;
    Genre(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    private static final Map<Integer, Genre> ENUM_MAP;

    static {
        Map<Integer, Genre> map = new ConcurrentHashMap<>();
        for (Genre instance : Genre.values()) {
            map.put(instance.getId(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Genre getGenreById(int genreId) {
        return ENUM_MAP.get(genreId);
    }
}
