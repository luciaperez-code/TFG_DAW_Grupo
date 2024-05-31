package com.edix.apirest.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.ItemsInOrder;

public interface ItemsInOrderRepository extends JpaRepository<ItemsInOrder, Integer>{
    
	@Modifying
    @Query("delete from ItemsInOrder o where o.order.idOrder = ?1")
    void deleteItemsInOrderByOrderId(long idOrder);
	
}
