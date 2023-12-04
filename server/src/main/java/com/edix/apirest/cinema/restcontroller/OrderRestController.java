package com.edix.apirest.cinema.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.OrderService;
import com.edix.apirest.cinema.service.UserService;

@RequestMapping("/orders")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderService pserv;
	
	@Autowired
	private UserService userv;
	
	// Ver el listado de orders
//	@GetMapping("/all")
//	public List<Order> order() {
//		List<Order> orders = pserv.findAll();		
//		return orders;
//	}
	
	// Ver el listado de productos
	@GetMapping("/all")
	public List<Order> pedidosUsuario() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userv.findUserByEmail(username);
		
		List<Order> pedidos = pserv.ordersByUser(user.getIdUser());		
		return pedidos;
	}
	
	// Ver un pedido según su ID
	@GetMapping("/ver-pedido/{id}")
	public Order verPedido(@PathVariable(name="id") int  idPedido) {
		Order pedido = pserv.findOrderById(idPedido);		
		return pedido;
	}
	
}
