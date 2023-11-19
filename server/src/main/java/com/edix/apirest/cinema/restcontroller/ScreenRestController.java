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

import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.service.ScreenService;

@RequestMapping("/screens")
@RestController
public class ScreenRestController {
	
	@Autowired
	private ScreenService sserv;
	
	// Ver la lista de productos
	@GetMapping("/all")
	public List<Screen> vertodos() {
		return sserv.findAll();
	}
	
	// Ver un producto según su ID
	@GetMapping("/{id}")
	public Screen getOneScreen(@PathVariable("id") int idScreen) {
		return sserv.findScreenById(idScreen);
	}
	
	@GetMapping("/type")
	public List<Screen> findScreenByType (@RequestParam("type") String type) {
		List<Screen>lista = sserv.findByScreenType("%" + type + "%");
		return lista;
	}
	
	@GetMapping("/type/3D")
	public List<Screen> find3DScreens () {
		List<Screen>lista = sserv.find3Dscreens();
		return lista;
	}
	
	@PostMapping("/add")
	public int addScreen(@RequestBody Screen screen) {
		int response = sserv.insertScreen(screen);
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
