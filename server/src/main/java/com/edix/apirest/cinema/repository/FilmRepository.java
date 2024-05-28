package com.edix.apirest.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	@Query("select f from Film f where f.genre like ?1")
	public Film findByGenre(String genre);

	@Query("select f from Film f where f.title like ?1")
	public List<Film> findByTitle(String nombre);
	
	@Query("select f from Film f order by score DESC")
	public List<Film> orderByScoreDesc();
	
	@Query("select f from Film f where f.released <= current_date")
	public List<Film> getAllReleasedFilms();

}
