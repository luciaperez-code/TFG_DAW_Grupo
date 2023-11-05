package com.edix.apirest.cinema.repository;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Integer>{
	

	
}
