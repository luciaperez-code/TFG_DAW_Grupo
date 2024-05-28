package com.edix.apirest.cinema.restcontroller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Projection;
import com.edix.apirest.cinema.service.ProjectionService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/projections")
@RestController
public class ProjectionRestController {
	
	@Autowired
	private ProjectionService pserv;
	
	@GetMapping("/all")
	public JSONResponse vertodos() {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findAllProjections();
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "findProjectionById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse findProjectionById(@PathVariable("id") int idProjection) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findProjectionById(idProjection);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "findProjectionById", this.getClass().getSimpleName(), e);
		}
		return response;

	}
	
	// Ver un Projection según su ID
	@GetMapping("/idFilm/{idFilm}")
	public JSONResponse findProjectionByFilmId(@PathVariable("idFilm") int idFilm) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findProjectionByFilm(idFilm);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "findProjectionByFilmId", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/date/{date}")
	public JSONResponse getProjectionsByDate (@PathVariable(name="date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date  date) {	
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.getProjectionByDate(date);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "getProjectionsByDate", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@PostMapping("/add-projection")
	public JSONResponse addProjection(@RequestBody Projection projection) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.insertarProjection(projection);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "addProjection", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/delete-projection/{id}")
	public JSONResponse deleteProjection(@PathVariable(name="id") int  idProjection) {	
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.deleteProjection(idProjection);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteProjection", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	// Página para editar el Projection
	@PostMapping("/edit-projection/{id}")
	public JSONResponse enviarFormularioEditar(@RequestBody Projection projection, @PathVariable(name="id") int idProjection) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.modifyProjection(projection, idProjection);
		}catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteProjection", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
}
