package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import com.edix.apirest.cinema.entities.Product;

public interface ProductService {

	// Lista de todos los productos
	List<Product> findAllProducts();
	
	// Buscar un producto por su ID
	Product findProductById(int idProduct);
	
	// Ordenación de productos
	List<Product> OrderByPriceAsc();
	List<Product> OrderByPriceDesc();

	// Borrar un producto
	int deleteProduct(int idProduct);
	
	// Insertar un producto
	int insertarProducto(Product product);
	
	// Modificar un producto
	int modifyProduct(Product product);
		
	// Listad de productos que coincidan con lo que se busca por el nombre
	List<Product>buscador(String nombre);

	// API REST
	Product buscadorNombre(String nombre);
	BigDecimal findPrecioByNombre (String nombre);
	
}
