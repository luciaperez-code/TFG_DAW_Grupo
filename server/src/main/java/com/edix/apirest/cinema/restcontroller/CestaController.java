package com.edix.apirest.cinema.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.OrderService;
 
@RestController
@RequestMapping("/shopping")
public class CestaController {
	
	@Autowired
	private OrderService oserv;
	
	// Ver la cesta con los productos añadidos
	@GetMapping("/getCesta")
	public List<Order> verCesta(@RequestBody User user) {
		
		List<Order> listCesta = oserv.ordersByUserAndStatus(user.getIdUser(), "Cesta");
		return listCesta;
	}
	
	// Guardar una cesta
	@PostMapping("/addCesta")
	public Order procAddCesta(@RequestBody List<LineaPedidoDto> lista, User user) {
		
		List<Order> listCesta = oserv.ordersByUserAndStatus(user.getIdUser(), "Cesta");
		Order order = new Order();
		if (listCesta.size() == 0) {
			order = oserv.buy(user, lista, null, "Cesta");
		}else {
			oserv.deleteOrder(listCesta.get(0).getIdOrder());
			order = oserv.buy(user, lista, null, "Cesta");
		}
		
		return order;
	}

	// Formulario para procesar el pedido, el usuario y la tarjeta 
	@PostMapping("/buy")
	public Order buy(@RequestBody List<LineaPedidoDto> lista, Card card, User user) {
		
		Order order = oserv.buy(user, lista, card, "Closed");
		
		return order;
	}

}
