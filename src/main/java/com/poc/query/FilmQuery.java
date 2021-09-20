package com.poc.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.poc.model.Film;
import com.poc.model.Hero;
import com.poc.service.GalaxyService;
import com.poc.service.HeroService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.graphql.*;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
@Slf4j
public class FilmQuery {
    @Inject
    GalaxyService service;
    @Inject
    HeroService heroService;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        // 2 secs
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) throws JsonProcessingException {
        // 3 secs
        log.info("Fetching heroes information");
        return heroService.getHerosByFilm(film);
    }

    public String bigTitle(@Source Film film) {
        return "something new";
    }
}
