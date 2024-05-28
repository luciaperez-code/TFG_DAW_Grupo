package com.edix.apirest.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.apirest.cinema.entities.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{

	
}
