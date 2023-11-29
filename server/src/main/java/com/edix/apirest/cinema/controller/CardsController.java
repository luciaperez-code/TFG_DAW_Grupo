package com.edix.apirest.cinema.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.Product;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.security.JwtResponse;
import com.edix.apirest.cinema.security.JwtTokenUtil;
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.service.UserService;


@RequestMapping("/cards")
@RestController
public class CardsController {
	
	@Autowired
	private CardService cserv;
	
	@Autowired
	private UserService userv;
	

	@GetMapping("/all")
	public List<Card> allCards() {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String username = authentication.getName();
		 List<Card> tarjetas = null;	     
	     if (username != null) {
	 		User user = userv.findUserByEmail(username);
			tarjetas = cserv.cardsByUser(user.getIdUser());
	     }
	     
		return tarjetas;
	}
	

	@PostMapping("/add-card")
	public List<Card> altaTarjeta(@RequestBody Card tarjeta) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User user = userv.findUserByEmail(username);
		user.addCard(tarjeta);
		userv.modificarUsuario(user);
		return user.getCards();
	}
	
	// Borrar tarjeta del usuario
	@GetMapping("/delete-card/{id}")
	public List<Card> eliminarTarjeta(@PathVariable("id") int idTarjeta) {
		
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
    	 List<Card> tarjetas = null;
		
	     if (username != null) {
	 		User user = userv.findUserByEmail(username);
			Card tarjeta = cserv.findCardById(idTarjeta);
			user.removeCard(tarjeta);
			userv.modificarUsuario(user);
			tarjetas = cserv.cardsByUser(user.getIdUser());
	     }
				
		return tarjetas;
	}
	
	// Página para ditar una tarjeta
	@PostMapping("/edit-card/{id}")
	public int editCard(@RequestBody Card card, @PathVariable(name="id") int idCard) {
		
		if (cserv.findCardById(idCard) == null){
			return 0;
		}else{
			card.setIdCard(idCard);
			if (cserv.modifyCard(card) == 1) {
				return 1;
			}else {
				return 0;
			}
		}
	}
	
	// Formatear la fecha para el formulario
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

}

