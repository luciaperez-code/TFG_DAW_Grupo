package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orepo;

	// Sacar lista de pedidos de un usuario
	@Override
	public List<Order> ordersByUser(int idUsuario) {
		return orepo.findByIdUser(idUsuario);
	}
	
	// Dar de alta un pedido
	@Override
	public int createOrder(Order pedido, String estado) {
		
		pedido.setStatus(estado);
		
		if (orepo.save(pedido) != null) {
			System.out.println(pedido);
			return 1;
		}else {
			return 0;
		}
		
	}


	// Buscar un pedido por su ID
	@Override
	public Order findOrderById(int idPedido) {
		return orepo.findById(idPedido).orElse(null);
	}

	@Override
	public List<Order> findAll() {
		return orepo.findAll();
	}
}
