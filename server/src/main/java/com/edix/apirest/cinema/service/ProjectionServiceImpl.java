package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Projection;
import com.edix.apirest.cinema.repository.ProjectionRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository prepo;
	
	// Lista de todos los Projections
	@Override
	public List<Projection> findAllProjections() {
		return prepo.findAll();
	}

	// Buscar un Projection por su ID
	@Override
	public Projection findProjectionById(int idProjection) {
		return prepo.findById(idProjection).orElse(null);
	}
	
	// Ordenar de Projections
	@Override
	public List<Projection> OrderByPriceAsc() {
		return prepo.orderByPriceAsc();
	}

	// Ordenación de Projections
	@Override
	public List<Projection> OrderByPriceDesc() {
		return prepo.orderByPriceDesc();
	}

	// Borrar un Projection
	@Override
	public int deleteProjection(int idProjection) {
		int filasBorradas = 0;
		try {
			prepo.deleteById(idProjection);
			filasBorradas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasBorradas;
	}

	// Insertar un Projection
	@Override
	public int insertarProjection(Projection Projection) {
		int filasInsertadas = 0;
		try {
			prepo.save(Projection);
			filasInsertadas = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}
	
	// Modificar un Projection
	@Override
	public int modifyProjection(Projection Projection) {
			
		int filasModificadas = 0;
		Projection prod = null;
		try {
			prod = prepo.findById(Projection.getIdProjection()).orElse(null);
			prod = Projection;
			prepo.save(prod);
			filasModificadas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasModificadas;
	}

	@Override
	public double findPrecioByNombre(String nombre) {
		return prepo.findPriceByName(nombre);
	}

	@Override
	public List<Projection> buscador(String nombre) {
		return prepo.buscadorNombre(nombre);
	}

}
