package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.Screen;

public interface FilmService {
	
	List<Film> findAll();

	Film findFilmById(int idFilm);
	
	Film findFilmByGenre(String genre);

	List<Film> findFilmByTitle(String name);
	
	List<Film> orderByScoreDesc();

}
