package com.edix.apirest.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	// Selecciona los Orders del cliente que le pasa por ID
	@Query("select p from Order p where p.user.idUser =?1")
	public List<Order> findByIdUser(int idUser);
	
	// Busca pedidos de un usuario con un status
	@Query("select p from Order p where p.user.idUser =?1 and p.status = ?2")
	public List<Order> findByUserAndStatus(int idUser, String status);
}
