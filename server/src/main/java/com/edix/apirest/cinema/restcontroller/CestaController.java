package com.edix.apirest.cinema.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.edix.apirest.cinema.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
 
@RestController
@RequestMapping("/shopping")
public class CestaController {
	
	@Autowired
	private OrderService oserv;
	
	@Autowired
	private UserService userv;
	
	// Ver la cesta con los productos añadidos
//	@GetMapping("/getCesta")
//	public List<Order> verCesta() {
//		
// 		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
//		User user = userv.findUserByEmail(username);
//
//		List<Order> listCesta = oserv.ordersByUserAndStatus(user.getIdUser(), "Cesta");
//		return listCesta;
//	}
//	
//	// Guardar una cesta
//	@PostMapping("/addBasket")
//	public Order addBasket(@RequestBody List<LineaPedidoDto> lista) {
//		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
//		User user = userv.findUserByEmail(username);
//		Order order = new Order();
//
//	    if (username != null) {
//	    	List<Order> listCesta = oserv.ordersByUserAndStatus(user.getIdUser(), "Cesta");
//			if (listCesta.size() == 0) {
//				order = oserv.buy(user, lista, "Cesta");
//			}else {
//				oserv.deleteOrder(listCesta.get(0).getIdOrder());
//				order = oserv.buy(user, lista, "Cesta");
//			}
//		}		
//		return order;
//	}

	// Formulario para procesar el pedido, el usuario logado y la tarjeta 
	@PostMapping("/buy")
	public Order buy(@RequestBody List<LineaPedidoDto> lista) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userv.findUserByEmail(username);
		
	    if (username != null) {
			Order order = oserv.buy(user, lista, "Closed");	
			return order;
	    }else {
	    	return null;
	    }
		
	}


}
