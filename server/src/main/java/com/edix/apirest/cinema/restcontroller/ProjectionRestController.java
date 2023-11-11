package com.edix.apirest.cinema.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Projection;
import com.edix.apirest.cinema.service.ProjectionService;

@RequestMapping("/projections")
@RestController
public class ProjectionRestController {
	
	@Autowired
	private ProjectionService pserv;
	
	// Ver la lista de Projections
	@GetMapping("/all")
	public List<Projection> vertodos() {
		return pserv.findAllProjections();
	}
	
	// Ver un Projection según su ID
	@GetMapping("/{id}")
	public Projection verUno(@PathVariable("id") int idProjection) {
		return pserv.findProjectionById(idProjection);
	}
	
	// Ver el precio del Projection que se pase
	@GetMapping("/verPrecio/{name}")
	public double getPrecioByName(@PathVariable("name") String nombre) {
		return pserv.findPrecioByNombre(nombre);
	}
	
	@PostMapping("/add-Projection")
	public int addProjection(@RequestBody Projection Projectiono) {
		int response = pserv.insertarProjection(Projectiono);
		return response;
	}
	
	// Borrar un Projection
	@GetMapping("/delete/{id}")
	public int delete(@PathVariable(name="id") int  idProjectiono) {	
		int borrado = pserv.deleteProjection(idProjectiono);
		return borrado;
	}
	
	// Página para editar el Projection
	@PostMapping("/edit/{id}")
	public int enviarFormularioEditar(@RequestBody Projection Projection, @PathVariable(name="id") int idProjection) {

		if (pserv.findProjectionById(idProjection) == null){
			return 0;
		}else{
			Projection.setIdProjection(idProjection);
			if (pserv.modifyProjection(Projection) == 1) {
				return 1;
			}else {
				return 0;
			}
		}
	}
	
	// Buscador de Projections
	@GetMapping("/search")
	public List<Projection> buscadorNombre (@RequestParam("nombre") String nombre) {
		List<Projection>lista = pserv.buscador("%" + nombre + "%");
		return lista;
	}
	
	@GetMapping("/list-projections/priceAsc")
	public List<Projection> orderByPriceAsc() {
		List<Projection> lista = pserv.OrderByPriceAsc();				
		return lista;
	}
	
	// Ordenar Projections Desc
	@GetMapping("/list-projections/priceDesc")
	public List<Projection> orderByPriceDesc() {
		List<Projection> lista = pserv.OrderByPriceDesc();
		return lista;
	}
	
}
