package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import com.edix.apirest.cinema.entities.Projection;

public interface ProjectionService {

	// Lista de todos los Projectionos
	List<Projection> findAllProjections();
	
	// Buscar un Projectiono por su ID
	Projection findProjectionById(int idProjection);
	
	// Ordenación de Projectionos
	List<Projection> OrderByPriceAsc();
	List<Projection> OrderByPriceDesc();

	// Borrar un Projectiono
	int deleteProjection(int idProjection);
	
	// Insertar un Projectiono
	int insertarProjection(Projection Projection);
	
	// Modificar un Projectiono
	int modifyProjection(Projection Projection);
		
	// Listad de Projectionos que coincidan con lo que se busca por el nombre
	List<Projection>buscador(String nombre);
	
	double findPrecioByNombre (String nombre);
	
}
