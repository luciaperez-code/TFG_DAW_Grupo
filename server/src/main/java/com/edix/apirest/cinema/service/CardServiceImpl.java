package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.repository.CardRepository;
import com.edix.apirest.cinema.repository.UserRepository;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardRepository crepo;
	
	@Autowired
	private UserRepository urepo;
	
	// Sacar el listado de tarjetas
	@Override
	public List<Card> findAllCards() {
		return crepo.findAll();
	}

	// Sacar el listado de tarjetas de un usuario
	@Override
	public List<Card> cardsByUser(int idUsuario) {
		return urepo.tarjetasPorUsuario(idUsuario);
	}

	// Modificar la tarjetas que te pasan
	@Override
	public int modifyCard(Card tarjeta) {
		int filasModificadas = 0;
		
		try {
			crepo.save(tarjeta);
			filasModificadas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return filasModificadas;
	}

	// Buscar la tarjetas por su ID
	@Override
	public Card findCardById(int idTarjeta) {
		return crepo.findById(idTarjeta).orElse(null);
	}

}
