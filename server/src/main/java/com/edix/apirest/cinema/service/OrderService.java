package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.User;

public interface OrderService {
	
	List<Order> findAll();
	
	// Sacar lista de pedidos de un usuario
	List<Order> ordersByUser(int idUsuario);
	
	List<Order> ordersByUserAndStatus(int idUsuario, String status);
	
	// Dar de alta un pedido
	Order createOrder(Order pedido, String estado);
	
	// Buscar un pedido por su ID
	Order findOrderById(int idPedido);
	
	Order buy(User user, List<LineaPedidoDto>lista, Card card, String status);
	
	int deleteOrder(int idPedido);

}
