package com.edix.apirest.cinema.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p order by price ASC")
	public List<Product> orderByPriceAsc();
	
	@Query("select p from Product p order by price DESC")
	public List<Product> orderByPriceDesc();
	
	@Query("select p from Product p where p.name like ?1")
	public List<Product>buscador(String name);

	// API REST
	@Query("select p from Product p where p.name like ?1")
	public Product buscadorNombre(String name);

	@Query("select p.price from Product p where p.name like ?1")
	public BigDecimal findPriceByName(String nombre);
	
}
