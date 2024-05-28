package com.edix.apirest.cinema.service;

import org.springframework.security.core.Authentication;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.JSONResponse;

public interface CardService {
	
	JSONResponse cardsByUser(Authentication authentication);
	JSONResponse addCard(Authentication authentication, Card card);
	JSONResponse deleteCard(Authentication authentication, int idCard);
	JSONResponse modifyCard(Card tarjeta, int idCard);

}
