package com.edix.apirest.cinema.service;

import java.util.Date;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Projection;

public interface ProjectionService {

	JSONResponse findAllProjections();
	JSONResponse findProjectionById(int idProjection);
	JSONResponse findProjectionByFilm(int idFilm);
	JSONResponse getProjectionByDate(Date date);

	JSONResponse insertarProjection(Projection projection);
	JSONResponse deleteProjection(int idProjection);
	JSONResponse modifyProjection(Projection projection, int idProjection);
				
	JSONResponse bookProjection(int idProjection, String normalSeats, String specialSeats);
}
