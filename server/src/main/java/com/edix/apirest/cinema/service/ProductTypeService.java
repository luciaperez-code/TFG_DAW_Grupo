package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import com.edix.apirest.cinema.entities.ProductType;

public interface ProductTypeService {

	// Lista de todos los tipos de productos
	List<ProductType> findAllProductTypes();
	
	ProductType findById(int id);
	
	int insertProductType (ProductType pt);
	
}
