package com.edix.apirest.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.ProductType;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.ProductService;
import com.edix.apirest.cinema.service.ProductTypeService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService pserv;
	
	@Autowired
	private ProductTypeService ptserv;
	
	// Mostrar lista de Productos
	@GetMapping("/lista-productos")
	public String verProductos(Model model) {
		List<Product> lista = pserv.findAllProducts();
		model.addAttribute("listaProductos", lista);
				
		return "lista-productos";
	}

	// Ver detalles de un produto
	@GetMapping("/ver-producto/{id}")
	public String verUno(Model model, @PathVariable(name="id") int  idProducto) {
		Product producto = pserv.findProductById(idProducto);
		model.addAttribute("producto", producto);
		
		return "ver-producto";
	}
	
	// Ordenar Productos Asc
	@GetMapping("/lista-productos/precioAsc")
	public String orderByPriceAsc(Model model) {
		List<Product> lista = pserv.OrderByPriceAsc();
		model.addAttribute("listaProductos", lista);
				
		return "lista-productos";
	}
	
	// Ordenar Productos Desc
	@GetMapping("/lista-productos/precioDesc")
	public String orderByPriceDesc(Model model) {
		List<Product> lista = pserv.OrderByPriceDesc();
		model.addAttribute("listaProductos", lista);
				
		return "lista-productos";
	}
		
	// Buscador de productos
	@GetMapping("/buscador")
	public String buscadorNombre (Model model, @RequestParam("nombre") String nombre) {
		List<Product>lista = pserv.buscador("%" + nombre + "%");
		model.addAttribute("listaProductos", lista);
			
		return "lista-productos";
	}
	
	// Borrar un producto
	@GetMapping("/borrar-producto/{id}")
	public String eliminar(Model model, @PathVariable(name="id") int  idProducto) {	
		if (pserv.deleteProduct(idProducto) == 1)
			model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
				+ "  Producto eliminado con éxito\r\n"
				+ "</div>");
		else
			model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  El producto no se ha podido eliminar\r\n"
				+ "</div>");
		
		return "forward:/lista-productos";
	}
	
	// Página de formulario para crear un producto nuevo
	@GetMapping("/alta-producto")
	public String formAltaProd(Model model) {
		List<ProductType> lista = ptserv.findAllProductTypes();
		model.addAttribute("listaProductType", lista);
		
		return "alta-producto";
	}
	
	// Formulario para crear un producto nuevo
	@PostMapping("/alta-producto")
	public String procesarFormulario(RedirectAttributes ratt, Model model,Product producto ) {
		pserv.insertarProducto(producto);
		
		ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
			+ "  El producto se ha añadido con éxito\r\n"
			+ "</div>");
		
		return "redirect:/lista-productos";
	}
	
	// Página para editar el producto
	@GetMapping("/editar-producto/{id}")
	public String enviarFormularioEditar(Model model, @PathVariable(name="id") int idProducto) {
		model.addAttribute("producto", pserv.findProductById(idProducto));
		
		List<ProductType> lista = ptserv.findAllProductTypes();
		model.addAttribute("listaProductType", lista);
		
		return "editar-producto";
	}
	
	// Formulario para editar el producto
	@PostMapping("/editar-producto/{id}")
	public String procesarFormularioEditar(Model model,Product producto,  @PathVariable(name="id") int  idProducto ) {
				
		if (pserv.findProductById(idProducto) == null){
			model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
				+ "  El producto no existe\r\n"
				+ "</div>");
		}else{
			producto.setIdProduct(idProducto);
			if (pserv.modifyProduct(producto) == 1) {
				model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Producto editado con éxito\r\n"
					+ "</div>");
			}else {
				model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  El producto no se ha podido editar\r\n"
					+ "</div>");
			}
		}
		return "redirect:/lista-productos";
		
	}
	
}
