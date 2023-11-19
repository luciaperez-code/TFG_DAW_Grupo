package com.edix.apirest.cinema.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.ItemsInOrder;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.OrderRepository;
import com.edix.apirest.cinema.repository.ProductRepository;
import com.edix.apirest.cinema.repository.ProjectionRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orepo;
	
	@Autowired
	private ProductRepository prepo;
	
	@Autowired
	private ProjectionRepository pjrepo;

	// Sacar lista de pedidos de un usuario
	@Override
	public List<Order> ordersByUser(int idUsuario) {
		return orepo.findByIdUser(idUsuario);
	}
	
	// Dar de alta un pedido
	@Override
	public Order createOrder(Order pedido, String estado) {
		
		pedido.setStatus(estado);
		
		Order order = orepo.save(pedido);
		if (order != null) {
			return order;
		}else {
			return null;
		}
		
	}
	
	@Override
	public int deleteOrder(int idPedido) {
		
		int filasBorradas = 0;
		try {
			orepo.deleteById(idPedido);
			filasBorradas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasBorradas;		
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

	@Override
	public Order buy(User user, List<LineaPedidoDto> lista, Card card, String status) {

		for (LineaPedidoDto ele: lista) {
			System.out.println("Linea de la compra: " + ele);
		}

		System.out.println("Lista => " + lista);
		
		Order p = new Order();
		p.setCreatedDate(new Date());
		p.setUser(user);
		if (card != null)
			p.setCard(card);
		
		List<ItemsInOrder> listaLP = new ArrayList<>();
		
		for (LineaPedidoDto ele: lista) {
			ItemsInOrder pep = new ItemsInOrder();
			pep.setOrder(p);
			if(ele.getIdProduct() != 0){
				pep.setProduct(prepo.findById(ele.getIdProduct()).orElse(null));
				pep.setPrice(pep.getProduct().getPrice());
				pep.setQuantity(ele.getQuantity());
			}
			if(ele.getIdProjection() != 0){
				pep.setProjection(pjrepo.findById(ele.getIdProjection()).orElse(null));
				pep.setPrice(pep.getProjection().getPrice());
			}
			System.out.println("Linea de pedido: " + pep);
			listaLP.add(pep);
		}
		
		 
		for (int i = 0; i < listaLP.size(); i++) {
			System.out.println("Elemento " + i + ":" + listaLP.get(i));
		}
		
		System.out.println(p);
		Order savedOrder = this.createOrder(p, status);		
				
		return savedOrder;
	}

	@Override
	public List<Order> ordersByUserAndStatus(int idUsuario, String status) {
		return orepo.findByUserAndStatus(idUsuario, status);
	}
}
