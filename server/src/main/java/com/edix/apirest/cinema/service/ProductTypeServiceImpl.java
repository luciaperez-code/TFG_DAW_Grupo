package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.repository.ProductTypeRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	private ProductTypeRepository ptrepo;

	@Override
	public JSONResponse findAllProductTypes() {
		JSONResponse response = new JSONResponse();
		List<ProductType> allProductTypes = ptrepo.findAll();
		if (allProductTypes != null) {
			Utils.createJSONResponseOk(response, allProductTypes);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productTypes :(");
		}
		return response;
	}

	@Override
	public JSONResponse findById(int id) {
		JSONResponse response = new JSONResponse();
		ProductType productType = ptrepo.findById(id).orElse(null);
		if (productType != null) {
			Utils.createJSONResponseOk(response, productType);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productType con ese ID :(");
		}
		return response;		
	}

	@Override
	public JSONResponse insertProductType(ProductType pt) {
		JSONResponse response = new JSONResponse();
		Utils.createJSONResponseOk(response, ptrepo.save(pt));
		return response;
	}

	@Override
	public JSONResponse deleteProduct(int idProductType) {
		JSONResponse response = new JSONResponse();
		ProductType productTypeToDelete = ptrepo.findById(idProductType).orElse(null);
		if (productTypeToDelete != null) {
			ptrepo.delete(productTypeToDelete);
			Utils.createJSONResponseOk(response, "ProductType borrado con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productTypes con ese ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse modifyProduct(ProductType pt, int idProductType) {
		JSONResponse response = new JSONResponse();
		if (ptrepo.findById(idProductType) != null) {
			pt.setIdProductType(idProductType);
			Utils.createJSONResponseOk(response, ptrepo.save(pt));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay productos con ese ID :(");
		}
		return response;
	}
	
}
