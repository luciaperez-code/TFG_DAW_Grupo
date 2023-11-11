package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Order;

public interface OrderService {
	
	List<Order> findAll();
	
	// Sacar lista de pedidos de un usuario
	List<Order> ordersByUser(int idUsuario);
	
	// Dar de alta un pedido
	int createOrder(Order pedido, String estado);
	
	// Buscar un pedido por su ID
	Order findOrderById(int idPedido);
}
