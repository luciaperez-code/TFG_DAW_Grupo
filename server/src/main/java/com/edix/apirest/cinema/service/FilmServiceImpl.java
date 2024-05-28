package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.repository.FilmRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class FilmServiceImpl implements FilmService{

	@Autowired
	private FilmRepository frepo;

	@Override
	public JSONResponse findAll() {
		JSONResponse response = new JSONResponse();
		List<Film> allFilms = frepo.findAll();
		if (allFilms != null) {
			Utils.createJSONResponseOk(response, allFilms);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay películas :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findAllReleasedFilms() {
		JSONResponse response = new JSONResponse();
		List<Film> allReleasedFilms = frepo.getAllReleasedFilms();

		if (allReleasedFilms != null) {
			Utils.createJSONResponseOk(response, allReleasedFilms);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay películas :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findFilmById(int idFilm) {
		JSONResponse response = new JSONResponse();
		Film filmById = frepo.findById(idFilm).orElse(null);
		if (filmById != null) {
			Utils.createJSONResponseOk(response, filmById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay películas con ese ID :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findFilmByTitle(String title) {
		JSONResponse response = new JSONResponse();
		List<Film> filmsByTitle = frepo.findByTitle("%" + title + "%");
		if (filmsByTitle != null && !filmsByTitle.isEmpty()) {
			Utils.createJSONResponseOk(response, filmsByTitle);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay películas con ese título :(");
		}
		return response;
	}
	
	@Override
	public Film findFilmByGenre(String genre) {
		// TODO Auto-generated method stub
		return frepo.findByGenre(genre);
	}

	@Override
	public JSONResponse insertFilm(Film film) {
		JSONResponse response = new JSONResponse();
		Utils.createJSONResponseOk(response, frepo.save(film));
		return response;
	}
	
	@Override
	public JSONResponse deleteFilm(int idFilm) {
		JSONResponse response = new JSONResponse();
		Film filmToDelete = frepo.findById(idFilm).orElse(null);
		if (filmToDelete != null) {
			frepo.delete(filmToDelete);
			Utils.createJSONResponseOk(response, "Película borrada con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay películas con ese id :(");
		}
		return response;
	}

	@Override
	public JSONResponse editFilm(Film film, int idFilm) {
		JSONResponse response = new JSONResponse();
		if (frepo.findById(idFilm) == null) {
			Utils.createJSONResponseFailed(response, 404, "No hay películas con ese id :(");
		}else {
			film.setIdFilm(idFilm);
			frepo.save(film);
			Utils.createJSONResponseOk(response, frepo.findById(idFilm));
		}
		return response;
	}

}
