package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.JSONResponse;

public interface OrderService {
	
	JSONResponse findAll();
	JSONResponse findOrdersByAuthenticatedUser(Authentication authentication);
	JSONResponse ordersByUser(int idUsuario);
	JSONResponse findOrderById(int idPedido);
	JSONResponse ordersByUserAndStatus(int idUsuario, String status);
	JSONResponse getCesta(Authentication authentication);
	
	JSONResponse buy(Authentication auth, int idCard);
	JSONResponse deleteOrder(int idPedido);
	JSONResponse addBasket(Authentication auth, List<LineaPedidoDto> lp);
}
