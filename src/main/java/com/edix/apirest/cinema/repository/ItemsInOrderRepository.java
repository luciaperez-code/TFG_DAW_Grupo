package com.edix.apirest.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.apirest.cinema.entities.ItemsInOrder;

public interface ItemsInOrderRepository extends JpaRepository<ItemsInOrder, Integer>{

}
