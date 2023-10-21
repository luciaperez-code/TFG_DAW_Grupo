package com.edix.apirest.cinema.repository;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.Product;

public interface FilmRepository extends JpaRepository<Film, Integer>{
	
	@Query("select f from Film p where f.genre like ?1")
	public Film findByGenre(String genre);

	@Query("select p.price from Product p where p.name like ?1")
	public Film findByName(String nombre);
	
}
