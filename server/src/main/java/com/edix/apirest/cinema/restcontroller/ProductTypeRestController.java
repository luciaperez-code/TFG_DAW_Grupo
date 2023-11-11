package com.edix.apirest.cinema.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.service.ProductTypeService;

@RequestMapping("/producttype")
@RestController
public class ProductTypeRestController {
	
	@Autowired
	private ProductTypeService ptserv;
	
	// Ver la lista de productos
	@GetMapping("/all")
	public List<ProductType> vertodos() {
		return ptserv.findAllProductTypes();
	}
	
	// Ver un producto según su ID
	@GetMapping("/{id}")
	public ProductType getOneProductType(@PathVariable("id") int idProductType) {
		return ptserv.findById(idProductType);
	}
	
	@PostMapping("/add")
	public int addProductType(@RequestBody ProductType producttype) {
		int response = ptserv.insertProductType(producttype);
		return response;
	}
	
//	// Borrar un producto
//	@GetMapping("/delete-product/{id}")
//	public int delete(@PathVariable(name="id") int  idProducto) {	
//		int borrado = pserv.deleteProduct(idProducto);
//		return borrado;
//	}
//		
//	// Página para editar el producto
//	@PostMapping("/edit-product/{id}")
//	public int enviarFormularioEditar(@RequestBody Product producto, @PathVariable(name="id") int idProducto) {
//
//		if (pserv.findProductById(idProducto) == null){
//			return 0;
//		}else{
//			producto.setIdProduct(idProducto);
//			if (pserv.modifyProduct(producto) == 1) {
//				return 1;
//			}else {
//				return 0;
//			}
//		}
//		
//	}
		
}
