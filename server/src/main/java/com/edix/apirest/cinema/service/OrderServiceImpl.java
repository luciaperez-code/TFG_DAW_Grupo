package com.edix.apirest.cinema.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.dtos.LineaPedidoDto;
import com.edix.apirest.cinema.entities.ItemsInOrder;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Order;
import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.CardRepository;
import com.edix.apirest.cinema.repository.ItemsInOrderRepository;
import com.edix.apirest.cinema.repository.OrderRepository;
import com.edix.apirest.cinema.repository.ProductRepository;
import com.edix.apirest.cinema.repository.ProjectionRepository;
import com.edix.apirest.cinema.utils.Utils;

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
	
	@Autowired
	private UserService userv;
	
	@Autowired
	private ItemsInOrderRepository iiorepo;
	
	@Override
	public JSONResponse findAll() {
		JSONResponse response = new JSONResponse();
		List<Order> allOrders = orepo.findAll();
		if (allOrders != null) {
			Utils.createJSONResponseOk(response, allOrders);
		}else {
			Utils.createJSONResponseFailed(response, 404, "Todavía no se ha hecho ningún pedido :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findOrdersByAuthenticatedUser(Authentication authentication) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		if (username != null) {
			User user = userv.userByEmail(username);
			List<Order> ordersByAuthUser = orepo.findByIdUser(user.getIdUser());
			if (ordersByAuthUser != null) {
				Utils.createJSONResponseOk(response, ordersByAuthUser);
			}else {
				Utils.createJSONResponseFailed(response, 404, "Este user todavía no tiene pedidos :(");
			}
		}
		return response;
	}
	
	@Override
	public JSONResponse ordersByUser(int idUsuario) {
		JSONResponse response = new JSONResponse();
		List<Order> ordersByUser = orepo.findByIdUser(idUsuario);
		if (ordersByUser != null) {
			Utils.createJSONResponseOk(response, ordersByUser);
		}else {
			Utils.createJSONResponseFailed(response, 404, "Este user no tiene pedidos :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findOrderById(int idPedido) {
		JSONResponse response = new JSONResponse();
		Order ordersById = orepo.findById(idPedido).get();
		if (ordersById != null) {
			Utils.createJSONResponseOk(response, ordersById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No existe pedido con este ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse ordersByUserAndStatus(int idUsuario, String status) {
		JSONResponse response = new JSONResponse();
		List<Order> ordersByUserAndStatus = orepo.findByUserAndStatus(idUsuario, status);
		if (ordersByUserAndStatus != null) {
			Utils.createJSONResponseOk(response, ordersByUserAndStatus);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No existen pedidos para este user en ese estado :(");
		}
		return response;
	}
	

	@Override
	public JSONResponse getCesta(Authentication authentication) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		if (username != null) {
			User user = userv.userByEmail(username);
			List<Order> cesta = orepo.findByUserAndStatus(user.getIdUser(), "Cesta");
			if (cesta != null) {
				Utils.createJSONResponseOk(response, cesta);
			}else {
				Utils.createJSONResponseFailed(response, 404, "Este user no tiene ningún pedido en estado cesta :(");
			}
		}
		return response;
	}
		
	@Override
	public JSONResponse deleteOrder(int idPedido) {
		
		JSONResponse response = new JSONResponse();
		Order orderToDelete = orepo.findById(idPedido).orElse(null);
		if (orderToDelete != null) {
			orepo.delete(orderToDelete);
			Utils.createJSONResponseOk(response, "Pedido borrado con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay pedidos con ese id :(");
		}
		return response;	
	}

	@Override
	public JSONResponse buy(Authentication authentication, int idCard) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		User user = userv.userByEmail(username);
		
        List<Order> orderBasket = orepo.findByUserAndStatus(user.getIdUser(), "Cesta");
		
    	if(orderBasket.size() == 0) {
    		Utils.createJSONResponseFailed(response, 404, "Este user no tiene nada en la cesta que comprar");
    		return response;
    	}

    	Order order = orderBasket.get(0);
	    order.setCard(crepo.findById(idCard).orElse(null));
	    if (order.getCard() == null) {
	    	Utils.createJSONResponseFailed(response, 404, "Tarjeta no encontrada");
	    	return response;
	    }
	    
		order.setCreatedDate(new Date());	    
        List<ItemsInOrder> itemsInOrder = order.getItemsInOrder();		
		for (ItemsInOrder ele: itemsInOrder) {
			
			if(ele.getProduct() != null){
				Product product = ele.getProduct();
				if (product.getProductType().getIdProductType() == 2) {
					if (product.getStock() > ele.getQuantity()) {
						product.setStock(product.getStock() - ele.getQuantity());
					}else {
						response.setErrorMessage("Producto " + product.getName() + "no añadido por falta de stock");
						break;
					}
				}
				prepo.save(product);
			}
			if(ele.getProjection() != null){
//				String[] normalSeats = new String[]{ele.getNormalSeats()};
//				String[] specialSeats = new String[]{ele.getSpecialSeats()};
				pjserv.bookProjection(ele.getProjection().getIdProjection(), ele.getNormalSeats(), ele.getSpecialSeats());
//				ele.setNormalSeats(Arrays.toString(normalSeats));
//				ele.setSpecialSeats(Arrays.toString(specialSeats));
				ele.setNormalSeats(ele.getNormalSeats());
				ele.setSpecialSeats(ele.getSpecialSeats());
			}
		}
		order.setStatus("Closed");

		Order savedOrder = orepo.save(order);
		Utils.createJSONResponseOk(response, savedOrder);
		
		System.out.println(savedOrder);
				
		return response;
	}

	@Override
	public JSONResponse addBasket(Authentication authentication, List<LineaPedidoDto> lista) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		User user = userv.userByEmail(username);
		
    	List<Order> listCesta = orepo.findByUserAndStatus(user.getIdUser(), "Cesta");

		Order order;
    	
    	if(listCesta.size() == 0) {
    		order = new Order();
    		order.setUser(user);
    		order.setStatus("Cesta");
    	}else {
     		order = listCesta.get(0);
     		order.getItemsInOrder().clear();
    	}
    	order.setItemsInOrder(new ArrayList<>());
		order.setCreatedDate(new Date());
				
		for (LineaPedidoDto ele: lista) {
			ItemsInOrder items = new ItemsInOrder();
			
			if(ele.getIdProduct() != null){
				items.setProduct(prepo.findById(ele.getIdProduct()).orElse(null));
				items.setPrice(items.getProduct().getPrice());
				items.setQuantity(ele.getQuantity());
			}
			if(ele.getIdProjection() != null){
				items.setProjection(pjrepo.findById(ele.getIdProjection()).orElse(null));
				items.setPrice(items.getProjection().getPrice());
//				String[] normalSeats = new String[]{ele.getOccupiedNormalSeats()};
//				String[] specialSeats = new String[]{ele.getOccupiedSpecialSeats()};
////				pjserv.bookProjection(items.getProjection().getIdProjection(), normalSeats, specialSeats);
//				items.setNormalSeats(Arrays.toString(normalSeats));
//				items.setSpecialSeats(Arrays.toString(specialSeats));
				items.setNormalSeats(ele.getOccupiedNormalSeats());
				items.setSpecialSeats(ele.getOccupiedSpecialSeats());
			}
			items.setOrder(order);
			System.out.println("Linea de pedido: " + items);
	        order.addItem(items);
		}
	    order.setCard(crepo.findById(lista.get(0).getIdCard()).orElse(null));
		Order savedOrder = orepo.save(order);
		Utils.createJSONResponseOk(response, savedOrder);
		return response;
	}
	
}
