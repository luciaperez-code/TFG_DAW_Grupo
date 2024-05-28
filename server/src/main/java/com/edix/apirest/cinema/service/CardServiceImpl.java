
package com.edix.apirest.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.CardRepository;
import com.edix.apirest.cinema.repository.UserRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository crepo;

	@Autowired
	private UserRepository urepo;

	@Autowired
	private UserService userv;

	@Override
	public JSONResponse cardsByUser(Authentication authentication) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		if (username != null) {
			User user = userv.userByEmail(username);
			Utils.createJSONResponseOk(response, urepo.tarjetasPorUsuario(user.getIdUser()));
		}
		return response;
	}

	@Override
	public JSONResponse addCard(Authentication authentication, Card card) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		User user = userv.userByEmail(username);
		user.addCard(card);
		urepo.save(user);
		Utils.createJSONResponseOk(response, user.getCards());

		return response;
	}

	@Override
	public JSONResponse deleteCard(Authentication authentication, int idCard) {
		JSONResponse response = new JSONResponse();
		String username = authentication.getName();
		if (username != null) {
			User user = userv.userByEmail(username);
			Card card = crepo.findById(idCard).orElse(null);
			if (card != null) {
				user.removeCard(card);
				urepo.save(user);
				crepo.delete(card);
				Utils.createJSONResponseOk(response, urepo.tarjetasPorUsuario(user.getIdUser()));
			} else {
				Utils.createJSONResponseFailed(response, 404, "Tarjeta no encontrada con ese ID");
			}
		}

		return response;
	}

	@Override
	public JSONResponse modifyCard(Card card, int idCard) {
		JSONResponse response = new JSONResponse();

		if (crepo.findById(idCard) == null) {
			Utils.createJSONResponseFailed(response, 404, "Tarjeta no encontrada con ese ID");
		} else {
			card.setIdCard(idCard);
			crepo.save(card);
			Utils.createJSONResponseOk(response, crepo.findById(idCard));
		}
		return response;
	}

}
