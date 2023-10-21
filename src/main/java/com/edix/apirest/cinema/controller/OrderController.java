package com.edix.apirest.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService pserv;
	
	// Ver el listado de productos
	@GetMapping("/lista-pedidos/{id}")
	public String pedidosUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		List<Order> pedidos = pserv.ordersByUser(idUsuario);
		model.addAttribute("pedidosUsuario", pedidos);
		
		return "lista-pedidos";
	}
	
	// Ver un pedido según su ID
	@GetMapping("/ver-pedido/{id}")
	public String verPedido(Model model, @PathVariable(name="id") int  idPedido) {
		Order pedido = pserv.findOrderById(idPedido);
		
		model.addAttribute("pedido", pedido);
		
		return "ver-pedido";
	}
	
	
}
