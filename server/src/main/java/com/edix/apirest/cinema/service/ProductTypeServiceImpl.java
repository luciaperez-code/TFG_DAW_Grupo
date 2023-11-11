package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.repository.ProductRepository;
import com.edix.apirest.cinema.repository.ProductTypeRepository;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

	@Autowired
	private ProductTypeRepository ptrepo;

	@Override
	public List<ProductType> findAllProductTypes() {
		return ptrepo.findAll();
	}

	@Override
	public ProductType findById(int id) {
		// TODO Auto-generated method stub
		return ptrepo.findById(id).orElse(null);
	}

	@Override
	public int insertProductType(ProductType pt) {
		int filasInsertadas = 0;
		try {
			ptrepo.save(pt);
			filasInsertadas = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}
	
}
