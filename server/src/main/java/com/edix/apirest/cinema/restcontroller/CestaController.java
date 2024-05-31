package com.edix.apirest.cinema.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.service.OrderService;
import com.edix.apirest.cinema.utils.Utils;
 
@RestController
@RequestMapping("/shopping")
public class CestaController {
	
	@Autowired
	private OrderService oserv;

	@GetMapping("/getCesta")
	public JSONResponse getCesta() {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = oserv.getCesta(authentication);	
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getCesta", this.getClass().getSimpleName(), e);
		}
		return response;	
	}
	

	@PostMapping("/addBasket")
	public JSONResponse addBasket(@RequestBody List<LineaPedidoDto> lista) {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = oserv.addBasket(authentication, lista);	
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "buy", this.getClass().getSimpleName(), e);
			System.out.println(e.getMessage());
		}
		return response;	
		
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
	}

	@PostMapping("/buy")
	public JSONResponse buy(@RequestBody List<LineaPedidoDto> lista, Card card) {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			int idCard = lista.get(0).getIdCard();
			response = oserv.buy(authentication, idCard);	
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "buy", this.getClass().getSimpleName(), e);
		}
		return response;		
	}


}
