package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.service.ProductService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/products")
@RestController
public class ProductRestController {

	@Autowired
	private ProductService pserv;

	@GetMapping("/all")
	public JSONResponse getAllProducts() {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findAllProducts();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllProducts", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse verUno(@PathVariable("id") int idProducto) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findProductById(idProducto);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllFilms", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/type/{idProductType}")
	public JSONResponse getProductByType(@PathVariable("idProductType") int idProductType) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.getProductByType(idProductType);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getProductByType", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/relatedFilm/{idFilm}")
	public JSONResponse getProductByRelatedFilm(@PathVariable("idFilm") int idFilm) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.getProductByRelatedFilm(idFilm);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getProductByRelatedFilm", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@PostMapping("/add-product")
	public JSONResponse addProduct(@RequestBody Product producto) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.addProduct(producto);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "addProduct", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/delete-product/{id}")
	public JSONResponse delete(@PathVariable(name = "id") int idProducto) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.deleteProduct(idProducto);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllFilms", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@PostMapping("/edit-product/{id}")
	public JSONResponse enviarFormularioEditar(@RequestBody Product product, @PathVariable(name = "id") int idProduct) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.modifyProduct(product, idProduct);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllFilms", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/search")
	public JSONResponse getProductsByName(@RequestParam("nombre") String name) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.buscador(name);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getProductsByName", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/list-products/priceAsc")
	public JSONResponse orderProductsByPriceAsc() {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.OrderByPriceAsc();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "orderProductsByPriceAsc", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/list-products/priceDesc")
	public JSONResponse orderProductsByPriceDesc() {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.OrderByPriceDesc();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "orderProductsByPriceDesc", this.getClass().getSimpleName(), e);
		}
		return response;
	}

}
