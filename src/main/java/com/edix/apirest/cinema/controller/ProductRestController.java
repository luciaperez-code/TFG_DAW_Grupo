package com.edix.apirest.cinema.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.service.ProductService;

@RequestMapping("/rest")
@RestController
public class ProductRestController {
	@Autowired
	private ProductService pserv;
	
	// Ver un producto según su ID
	@GetMapping("/uno/{id}")
	public Product verUno(@PathVariable("id") int idProducto) {
		return pserv.findProductById(idProducto);
	}
	
	// Ver la lista de productos
	@GetMapping("/todos")
	public List<Product> todos(){
		return pserv.findAllProducts();
	}
	
	// Ver el precio del producto que se pase
	@GetMapping("/verPrecio/{name}")
	public BigDecimal getPrecioByName(@PathVariable("name") String nombre) {
		return pserv.findPrecioByNombre(nombre);
	}
}
