package com.poc.query;

import com.poc.model.Film;
import com.poc.model.Hero;
import com.poc.service.GalaxyService;
import org.eclipse.microprofile.graphql.*;

import javax.inject.Inject;
import java.util.List;

@GraphQLApi
public class FilmQuery {
    @Inject
    GalaxyService service;

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }
}
