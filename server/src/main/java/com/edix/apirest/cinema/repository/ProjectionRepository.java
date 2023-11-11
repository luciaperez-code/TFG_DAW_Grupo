package com.edix.apirest.cinema.repository;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Integer>{
	
	@Query("select p from Projection p order by price ASC")
	public List<Projection> orderByPriceAsc();
	
	@Query("select p from Projection p order by price DESC")
	public List<Projection> orderByPriceDesc();

	@Query("select p from Projection p where p.film.title like ?1")
	public List<Projection> buscadorNombre(String name);

	@Query("select p.price from Projection p where p.film.title like ?1")
	public double findPriceByName(String nombre);
	
}
