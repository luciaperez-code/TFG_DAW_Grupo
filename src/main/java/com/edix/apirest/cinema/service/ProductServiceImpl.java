package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository prepo;
	
	// Lista de todos los productos
	@Override
	public List<Product> findAllProducts() {
		return prepo.findAll();
	}

	// Buscar un producto por su ID
	@Override
	public Product findProductById(int idProducto) {
		return prepo.findById(idProducto).orElse(null);
	}
	
	// Ordenación de productos
	@Override
	public List<Product> OrderByPriceAsc() {
		return prepo.orderByPriceAsc();
	}

	// Ordenación de productos
	@Override
	public List<Product> OrderByPriceDesc() {
		return prepo.orderByPriceDesc();
	}

	// Borrar un producto
	@Override
	public int deleteProduct(int idProducto) {
		int filasBorradas = 0;
		try {
			prepo.deleteById(idProducto);
			filasBorradas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasBorradas;
	}

	// Insertar un producto
	@Override
	public int insertarProducto(Product producto) {
		int filasInsertadas = 0;
		try {
			prepo.save(producto);
			filasInsertadas = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}
	
	// Modificar un producto
	@Override
	public int modifyProduct(Product producto) {
			
		int filasModificadas = 0;
		Product prod = null;
		try {
			prod = prepo.findById(producto.getIdProduct()).orElse(null);
			prod = producto;
			prepo.save(prod);
			filasModificadas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasModificadas;
	}

	// Listad de productos que coincidan con lo que se busca por el nombre
	@Override
	public List<Product> buscador(String nombre) {
		return prepo.buscador(nombre);
	}


	// API REST

	@Override
	public Product buscadorNombre(String nombre) {
		return prepo.buscadorNombre(nombre);
	}

	@Override
	public BigDecimal findPrecioByNombre(String nombre) {
		return prepo.findPriceByName(nombre);
	}

}
