package com.edix.apirest.cinema.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.service.FilmService;

@RequestMapping("/films")
@RestController
public class FilmRestController {
	
	@Autowired
	private FilmService fserv;
	
	// Ver la lista de Films
	@GetMapping("/all")
	public List<Film> vertodos() {
		return fserv.findAll();
	}
	
	// Ver Film según su ID
	@GetMapping("/{id}")
	public Film verUno(@PathVariable("id") int idFilm) {
		return fserv.findFilmById(idFilm);
	}
		
//	@PostMapping("/add-Film")
//	public int addFilm(@RequestBody Film Filmo) {
//		int response = fserv.insertFilm(Filmo);
//		return response;
//	}
//	
//	// Borrar un Film
//	@GetMapping("/delete/{id}")
//	public int delete(@PathVariable(name="id") int  idFilmo) {	
//		int borrado = fserv.deleteFilm(idFilmo);
//		return borrado;
//	}
//	
//	// Página para editar el Film
//	@PostMapping("/edit/{id}")
//	public int editFilm(@RequestBody Film Film, @PathVariable(name="id") int idFilm) {
//
//		if (fserv.findFilmById(idFilm) == null){
//			return 0;
//		}else{
//			Film.setIdFilm(idFilm);
//			if (fserv.modifyFilm(Film) == 1) {
//				return 1;
//			}else {
//				return 0;
//			}
//		}
//	}
	
	// Buscador de Films
	@GetMapping("/search")
	public List<Film> buscadorNombre (@RequestParam("title") String title) {
		List<Film>lista = fserv.findFilmByTitle("%" + title + "%");
		return lista;
	}

	
}
