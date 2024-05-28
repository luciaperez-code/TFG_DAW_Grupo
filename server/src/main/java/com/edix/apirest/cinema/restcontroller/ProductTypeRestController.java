package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.service.ProductTypeService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/producttype")
@RestController
public class ProductTypeRestController {
	
	@Autowired
	private ProductTypeService ptserv;
	
	@GetMapping("/all")
	public JSONResponse getAllProductTypes() {
		JSONResponse response = new JSONResponse();
		try {
			response = ptserv.findAllProductTypes();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllProductTypes", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse getOneProductType(@PathVariable("id") int idProductType) {
		JSONResponse response = new JSONResponse();
		try {
			response = ptserv.findById(idProductType);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getOneProductType", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@PostMapping("/add-productType")
	public JSONResponse addProductType(@RequestBody ProductType producttype) {
		JSONResponse response = new JSONResponse();
		try {
			response = ptserv.insertProductType(producttype);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "addProductType", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/delete-productType/{id}")
	public JSONResponse deleteProductType(@PathVariable(name="id") int  idProducto) {	
		JSONResponse response = new JSONResponse();
		try {
			response = ptserv.deleteProduct(idProducto);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteProductType", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@PostMapping("/edit-productType/{id}")
	public JSONResponse editProductType(@RequestBody ProductType productType, @PathVariable(name="id") int idProductType) {
		JSONResponse response = new JSONResponse();
		try {
			response = ptserv.modifyProduct(productType, idProductType);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "editProductType", this.getClass().getSimpleName(), e);
		}
		return response;		
	}
		
}
