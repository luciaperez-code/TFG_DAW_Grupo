package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.JSONResponse;

public interface FilmService {
	
	JSONResponse findAll();
	JSONResponse findAllReleasedFilms();
	JSONResponse findFilmById(int idFilm);
	JSONResponse findFilmByTitle(String name);
	Film findFilmByGenre(String genre);

	JSONResponse insertFilm(Film film);
	JSONResponse deleteFilm(int idFilm);
	JSONResponse editFilm(Film film, int idFilm);
}
