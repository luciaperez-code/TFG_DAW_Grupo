

package com.edix.apirest.cinema.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.ItemsInOrder;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.ProductRepository;
import com.edix.apirest.cinema.repository.ProjectionRepository;
import com.edix.apirest.cinema.service.OrderService;
import com.edix.apirest.cinema.service.ProductService;
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.service.UserService;
 
@Controller
@RequestMapping("/cesta")
public class CestaController {
	@Autowired
	private ProductRepository prodrepo;
	
	@Autowired
	private ProjectionRepository projrepo;
	
	@Autowired
	private CardService tserv;
	
	@Autowired
	private OrderService oserv;
	
	@Autowired
	private UserService userv;
	
	// Ver la cesta con los productos añadidos
//	@GetMapping("/ver")
//	public String verCesta(Model model, HttpSession sesion) {
//		List<LineaPedidoDto> lista = (List<LineaPedidoDto>)sesion.getAttribute("cesta");
//		model.addAttribute("cesta", lista);
//		
//		return "carrito";
//	}
//	
//	// Formulario para añadir el producto a la cesta
//	@PostMapping("/addCesta/{id}")
//	public String procAddCesta(RedirectAttributes ratt, Model model, @PathVariable(name="id") int idProducto, HttpSession sesion, LineaPedidoDto linea) {
//		linea.setIdProducto(idProducto);
//		linea.setPrecioVenta(Double.parseDouble(prodrepo.findById(idProducto).orElse(null).getPrice().toString()));
//		List<LineaPedidoDto> cesta = (List<LineaPedidoDto>)sesion.getAttribute("cesta");
//		System.out.println("Cesta: " + cesta);
//		
//		if (cesta == null) {
//			System.out.println("ArrayList creado!");
//			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//					+ "  El producto se ha a la cesta\r\n"
//					+ "</div>");
//			cesta = new ArrayList<>();
//		}
//		if (!cesta.contains(linea))
//			cesta.add(linea);	
//			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//					+ "  El producto se ha añadido a la cesta\r\n"
//					+ "</div>");
//		
//		for(int i = 0; i < cesta.size(); i++) {   
//		    System.out.print("Articulo en la cesta: "+ i +":"+ cesta.get(i));
//		}  
//		
//		sesion.setAttribute("cesta", cesta);
//		
//		return "redirect:/lista-productos";
//	}
//
//	// Ir a la página de la cesta para ver el pedido
//	@GetMapping("/comprar/{idUsuario}")
//	public String comprarCesta(Model model, HttpSession sesion, @PathVariable(name="idUsuario") int idUsuario) {
//		List<LineaPedidoDto> cesta = (List<LineaPedidoDto>)sesion.getAttribute("cesta");
//		model.addAttribute("cesta", cesta);
//		
//		//Usuario user = (Usuario)sesion.getAttribute("usuario");
//		
//		//System.out.println("Usuario en sesión = " + user + "!");
//		List<Card> tuser = tserv.cardsByUser(idUsuario);
//		model.addAttribute("TarjetasUsuario", tuser);
//		
//		sesion.setAttribute("userCompra", userv.findById(idUsuario));
//		System.out.println("Usuario que compra es: " + userv.findById(idUsuario));
//		
//		return "carrito";
//	}
//	
//	// Formulario para procesar el pedido, el usuario, la dirección y la tarjeta 
//	@PostMapping("/comprar")
//	public String comprarPost(RedirectAttributes ratt, Model model, HttpSession sesion, Card tarjeta) {
//		List<LineaPedidoDto> lista = (List<LineaPedidoDto>)sesion.getAttribute("cesta");
//		
//		for (LineaPedidoDto ele: lista) {
//			System.out.println("Linea de la compra: " + ele);
//			
//		}
//		System.out.println("Lista => " + lista);
//		Order p = new Order();
//
//		p.setCard(tarjeta);
//
//		//p.setEstado("Terminado");
//		p.setCreatedDate(new Date());
//		User user = (User)sesion.getAttribute("userCompra");
//		p.setUser(user);
//		
//		List<ItemsInOrder> listaLP = new ArrayList<>();
//		
//		for (LineaPedidoDto ele: lista) {
//			ItemsInOrder pep = new ItemsInOrder();
//			pep.setQuantity(ele.getCantidad());
//			pep.setOrder(p);
//			pep.setProducto(prodrepo.findById(ele.getIdProducto()).orElse(null));
//			pep.setProjection(projrepo.findById(ele.getIdProyection()).orElse(null));
//			pep.setPrice(BigDecimal.valueOf(ele.getPrecioVenta()));
//			System.out.println("Linea de pedido: " + pep);
//			listaLP.add(pep);
//		}
//		
//		 
//		for (int i = 0; i < listaLP.size(); i++) {
//			System.out.println("Elemento " + i + ":" + listaLP.get(i));
//		}
//		
//		System.out.println(p);
//		oserv.createOrder(p, "Terminado");
//		
//		sesion.setAttribute("cesta", null);
//		
//		ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//				+ "  Compra realizada con éxito :) \r\n"
//				+ "</div>");
//		
//		return "redirect:/lista-productos";
//	}
	
}
