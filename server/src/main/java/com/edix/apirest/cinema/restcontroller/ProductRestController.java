package com.edix.apirest.cinema.restcontroller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.service.ProductService;

@RequestMapping("/products")
@RestController
public class ProductRestController {
	
	@Autowired
	private ProductService pserv;
	
	// Ver la lista de productos
	@GetMapping("/all")
	public List<Product> vertodos() {
		return pserv.findAllProducts();
	}
	
	// Ver un producto según su ID
	@GetMapping("/{id}")
	public Product verUno(@PathVariable("id") int idProducto) {
		return pserv.findProductById(idProducto);
	}
		
	// Ver el precio del producto que se pase
	@GetMapping("/verPrecio/{name}")
	public BigDecimal getPrecioByName(@PathVariable("name") String nombre) {
		return pserv.findPrecioByNombre(nombre);
	}
	
	@PostMapping("/add-product")
	public int addProduct(@RequestBody Product producto) {
		int response = pserv.insertarProducto(producto);
		return response;
	}
	
	// Borrar un producto
	@GetMapping("/delete-product/{id}")
	public int delete(@PathVariable(name="id") int  idProducto) {	
		int borrado = pserv.deleteProduct(idProducto);
		return borrado;
	}
		
	// Página para editar el producto
	@PostMapping("/edit-product/{id}")
	public int enviarFormularioEditar(@RequestBody Product producto, @PathVariable(name="id") int idProducto) {

		if (pserv.findProductById(idProducto) == null){
			return 0;
		}else{
			producto.setIdProduct(idProducto);
			if (pserv.modifyProduct(producto) == 1) {
				return 1;
			}else {
				return 0;
			}
		}
		
	}
	
	// Buscador de productos
	@GetMapping("/search")
	public List<Product> buscadorNombre (@RequestParam("nombre") String nombre) {
		List<Product>lista = pserv.buscador("%" + nombre + "%");
			
		return lista;
	}
	
	@GetMapping("/list-products/priceAsc")
	public List<Product> orderByPriceAsc() {
		List<Product> lista = pserv.OrderByPriceAsc();				
		return lista;
	}
	
	// Ordenar Productos Desc
	@GetMapping("/list-products/priceDesc")
	public List<Product> orderByPriceDesc() {
		List<Product> lista = pserv.OrderByPriceDesc();
		return lista;
	}
	
}
