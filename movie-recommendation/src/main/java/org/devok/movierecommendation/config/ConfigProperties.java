package org.devok.movierecommendation.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigProperties {
    @Value("${tmdb_api_key}")
    private String tmdbApiKey;
}
