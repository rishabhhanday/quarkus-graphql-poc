package com.poc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.model.Film;
import com.poc.model.Hero;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Slf4j
public class HeroService {
    @Inject
    CachingService cachingService;
    @Inject
    GalaxyService galaxyService;

    // Lazy population implementation.
    public List<Hero> getHerosByFilm(Film film) throws JsonProcessingException {
        List<Hero> heroes = cachingService.getHeroesByFilmID(film.episodeID);
        if (heroes.isEmpty()) {
            log.info("cache miss, fetching heroes result from some API .");
            heroes = galaxyService.getHeroesByFilm(film);

            log.info("Fetched data now caching the fetched data");
            cachingService.setHeroesByFilmID(film.episodeID, heroes);
            return heroes;
        } else {
            log.info("Cache HIT");
            return heroes;
        }
    }
}
