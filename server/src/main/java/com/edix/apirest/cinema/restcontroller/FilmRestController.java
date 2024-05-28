package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.service.FilmService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/films")
@RestController
public class FilmRestController {
	
	@Autowired
	private FilmService fserv;
	
	@GetMapping("/all")
	public JSONResponse getAllFilms() {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.findAll();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllFilms", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/allReleasedFilms")
	public JSONResponse getAllReleasedFilms() {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.findAllReleasedFilms();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllReleasedFilms", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse getFilmById(@PathVariable("id") int idFilm) {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.findFilmById(idFilm);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getFilmById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@PostMapping("/add-film")
	public JSONResponse addFilm(@RequestBody Film film) {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.insertFilm(film);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "addFilm", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/delete-film/{id}")
	public JSONResponse deleteFilm(@PathVariable(name="id") int  idFilm) {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.deleteFilm(idFilm);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteFilm", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@PostMapping("/edit-film/{id}")
	public JSONResponse editFilm(@RequestBody Film film, @PathVariable(name="id") int idFilm) {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.editFilm(film, idFilm);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "editFilm", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/search")
	public JSONResponse buscadorNombre (@RequestParam("title") String title) {
		JSONResponse response = new JSONResponse();
		try {
			response = fserv.findFilmByTitle(title);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getFilmById", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	
}
