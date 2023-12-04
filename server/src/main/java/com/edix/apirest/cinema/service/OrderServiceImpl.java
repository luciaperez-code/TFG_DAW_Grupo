package com.edix.apirest.cinema.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.ItemsInOrder;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.CardRepository;
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
	
	@Autowired
	private ProjectionService pjserv;
	
	@Autowired
	private CardRepository crepo;

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
	public Order buy(User user, List<LineaPedidoDto> lista, String status) {

		for (LineaPedidoDto ele: lista) {
			System.out.println("Linea de la compra: " + ele);
		}

		System.out.println("Lista => " + lista);
		
		Order order = new Order();
		order.setCreatedDate(new Date());
		order.setUser(user);
		
		
		List<ItemsInOrder> listaLP = new ArrayList<>();
		
		for (LineaPedidoDto ele: lista) {
			ItemsInOrder items = new ItemsInOrder();
			items.setOrder(order);
			
			if(ele.getIdProduct() != null){
				items.setProduct(prepo.findById(ele.getIdProduct()).orElse(null));
				items.setPrice(items.getProduct().getPrice());
				items.setQuantity(ele.getQuantity());
				if (items.getProduct().getStock() != 0) {
					items.getProduct().setStock(items.getProduct().getStock() - ele.getQuantity());
				}
			}
			if(ele.getIdProjection() != null){
				items.setProjection(pjrepo.findById(ele.getIdProjection()).orElse(null));
				items.setPrice(items.getProjection().getPrice());
				String[] normalSeats = new String[]{ele.getOccupiedNormalSeats()};
				String[] specialSeats = new String[]{ele.getOccupiedSpecialSeats()};
				pjserv.bookProjection(items.getProjection().getIdProjection(), normalSeats, specialSeats);
				items.setNormalSeats(Arrays.toString(normalSeats));
				items.setSpecialSeats(Arrays.toString(specialSeats));
			}
			order.setCard(crepo.findById(ele.getIdCard()).orElse(null));

			System.out.println("Linea de pedido: " + items);
			listaLP.add(items);
		}
		order.setItemsInOrder(listaLP);
		
//		System.out.println(p);
		Order savedOrder = this.createOrder(order, status);		
				
		return savedOrder;
	}

	@Override
	public List<Order> ordersByUserAndStatus(int idUsuario, String status) {
		return orepo.findByUserAndStatus(idUsuario, status);
	}
}
