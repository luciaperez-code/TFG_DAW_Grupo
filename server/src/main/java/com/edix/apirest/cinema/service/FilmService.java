package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.Screen;

public interface FilmService {
	
	// Buscar la tarjetas por su ID
	Film findFilmById(int idFilm);
	
	Film findFilmByGenre(String genre);
	Film findFilmByName(String name);

}
