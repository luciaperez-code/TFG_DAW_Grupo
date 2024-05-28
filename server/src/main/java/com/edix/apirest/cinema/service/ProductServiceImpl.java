package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.repository.ProductRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository prepo;
	
	@Override
	public JSONResponse findAllProducts() {
		JSONResponse response = new JSONResponse();
		List<Product> allProducts = prepo.findAll();
		if (allProducts != null) {
			Utils.createJSONResponseOk(response, allProducts);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse getProductByType(int idProductType) {
		JSONResponse response = new JSONResponse();
		List<Product> productsByType = prepo.getProductByType(idProductType);
		if (productsByType != null) {
			Utils.createJSONResponseOk(response, productsByType);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos de este tipo :(");
		}
		return response;
	}

	@Override
	public JSONResponse getProductByRelatedFilm(int idFilm) {
		JSONResponse response = new JSONResponse();
		List<Product> productsByRelatedFilm = prepo.getProductByRelatedFilm(idFilm);
		if (productsByRelatedFilm != null) {
			Utils.createJSONResponseOk(response, productsByRelatedFilm);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos relacionados con esta peli :(");
		}
		return response;
	}

	@Override
	public JSONResponse findProductById(int idProducto) {
		JSONResponse response = new JSONResponse();
		Product productById = prepo.findById(idProducto).orElse(null);
		if (productById != null) {
			Utils.createJSONResponseOk(response, productById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos relacionados con esta peli :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse OrderByPriceAsc() {
		JSONResponse response = new JSONResponse();
		List<Product> productsByPriceAsc = prepo.orderByPriceAsc();
		if (productsByPriceAsc !=null){
			Utils.createJSONResponseOk(response, productsByPriceAsc);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos");
		}
		return response;
	}

	@Override
	public JSONResponse OrderByPriceDesc() {
		JSONResponse response = new JSONResponse();
		List<Product> productsByPriceDesc = prepo.orderByPriceDesc();

		if (productsByPriceDesc !=null){
			Utils.createJSONResponseOk(response, productsByPriceDesc);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos");
		}
		return response;
	}
	
	@Override
	public JSONResponse addProduct(Product product) {
		JSONResponse response = new JSONResponse();
		Utils.createJSONResponseOk(response, prepo.save(product));
		return response;
	}
	
	@Override
	public JSONResponse deleteProduct(int idProduct) {
		JSONResponse response = new JSONResponse();
		Product productToDelete = prepo.findById(idProduct).orElse(null);
		if (productToDelete != null) {
			prepo.delete(productToDelete);
			Utils.createJSONResponseOk(response, "Producto borrado con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos con ese id :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse modifyProduct(Product product, int idProduct) {
		JSONResponse response = new JSONResponse();
		if (prepo.findById(idProduct) != null) {
			product.setIdProduct(idProduct);
			Utils.createJSONResponseOk(response, prepo.save(product));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos con ese id :(");
		}
		return response;
	}

	@Override
	public JSONResponse buscador(String nombre) {
		JSONResponse response = new JSONResponse();
		List<Product> productsByName = prepo.buscador("%" + nombre + "%");
		if (productsByName !=null){
			Utils.createJSONResponseOk(response, productsByName);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos con ese nombre");
		}
		return response;
		
	}

}
