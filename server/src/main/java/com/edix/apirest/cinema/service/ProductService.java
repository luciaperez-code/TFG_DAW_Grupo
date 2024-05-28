package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Product;

public interface ProductService {

	JSONResponse findAllProducts();
	JSONResponse getProductByType(int idProductType);
	JSONResponse getProductByRelatedFilm(int idFilm);
	JSONResponse findProductById(int idProduct);
	
	JSONResponse OrderByPriceAsc();
	JSONResponse OrderByPriceDesc();

	JSONResponse addProduct(Product product);
	JSONResponse deleteProduct(int idProduct);
	JSONResponse modifyProduct(Product product, int idProduct);
		
	JSONResponse buscador(String nombre);
	
}
