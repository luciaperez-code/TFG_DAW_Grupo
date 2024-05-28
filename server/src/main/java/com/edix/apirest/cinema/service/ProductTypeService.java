package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.ProductType;

public interface ProductTypeService {

	JSONResponse findAllProductTypes();
	JSONResponse findById(int id);
	JSONResponse insertProductType (ProductType pt);
	JSONResponse deleteProduct(int idProductType);
	JSONResponse modifyProduct(ProductType pt, int idProductType);	
}
