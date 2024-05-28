package com.edix.apirest.cinema.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("select u from User u where u.email = ?1")
	public User findByEmail(String email);
		
	@Query("select u.cards from User u where u.idUser = ?1")
	public List<Card> tarjetasPorUsuario(int idUsuario);
	
}

