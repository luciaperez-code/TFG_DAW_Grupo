package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/cards")
@RestController
public class CardsRestController {

	@Autowired
	private CardService cserv;

	@GetMapping("/all")
	public JSONResponse allCards() {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = cserv.cardsByUser(authentication);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "allCards", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@PostMapping("/add-card")
	public JSONResponse addCard(@RequestBody Card card) {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = cserv.addCard(authentication, card);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "addCard", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@GetMapping("/delete-card/{id}")
	public JSONResponse deleteCard(@PathVariable("id") int idCard) {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = cserv.deleteCard(authentication, idCard);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteCard", this.getClass().getSimpleName(), e);
		}
		return response;
	}

	@PostMapping("/edit-card/{id}")
	public JSONResponse editCard(@RequestBody Card card, @PathVariable(name = "id") int idCard) {
		JSONResponse response = new JSONResponse();
		try {
			response = cserv.modifyCard(card, idCard);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "editCard", this.getClass().getSimpleName(), e);
		}
		return response;
	}

}
