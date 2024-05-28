package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.service.OrderService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/orders")
@RestController
public class OrderRestController {
	
	@Autowired
	private OrderService pserv;
		
	@GetMapping("/allOrders")
	public JSONResponse allOrders() {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findAll();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "allOrders", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/all")
	public JSONResponse ordersByUserAuthenticated() {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = pserv.findOrdersByAuthenticatedUser(authentication);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "ordersByUserAuthenticated", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/user/{idUser}")
	public JSONResponse ordersByUser(@PathVariable(name="idUser") int  idUser) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.ordersByUser(idUser);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "ordersByUser", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@GetMapping("/detail/{id}")
	public JSONResponse getOrderById(@PathVariable(name="id") int  idPedido) {
		JSONResponse response = new JSONResponse();
		try {
			response = pserv.findOrderById(idPedido);	
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getOrderById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
}
