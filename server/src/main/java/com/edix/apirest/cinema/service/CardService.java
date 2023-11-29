package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Card;

public interface CardService {

	// Sacar el listado de tarjetas
	List<Card> findAllCards();
	
	// Sacar el listado de tarjetas de un usuario
	List<Card> cardsByUser(int idUsuario);
	
	// Modificar la tarjetas que te pasan
	int modifyCard(Card tarjeta);
	
	// Buscar la tarjetas por su ID
	Card findCardById(int idTarjeta);
	
	int insertCard(Card card);
	
	int deleteCard(int idCard);

}
