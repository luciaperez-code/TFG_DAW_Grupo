package com.edix.apirest.cinema.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("select u from Usuario u where u.username = ?1")
	public User buscarPorUsername(String username);
		
	@Query("select u.tarjetas from Usuario u where u.idUsuario = ?1")
	public List<Card> tarjetasPorUsuario(int idUsuario);
	
	/*
	@Query("SELECT d.localidad, COUNT(*) as total_clientes FROM usuarios u  INNER JOIN usuarios_con_direcciones uc ON u.id_usuario = uc.id_usuario INNER JOIN direcciones d ON uc.id_direccion = d.id_direccion GROUP BY d.localidad")
	public int numeroPorLocalidad();
	*/
	
}

