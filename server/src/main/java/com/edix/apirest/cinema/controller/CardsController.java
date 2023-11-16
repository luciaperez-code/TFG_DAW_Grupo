package com.edix.apirest.cinema.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.service.UserService;


@RequestMapping("/cards")
@RestController
public class CardsController {
	
	@Autowired
	private CardService cserv;
	
	@Autowired
	private UserService userv;
		
//	// Ir al formulario para añadir tarjeta del Usuario
//	@GetMapping("/alta-tarjeta/{id}")
//	public String altaTarjeta(@PathVariable(name="id") int  idUsuario) {
//		User user = userv.findById(idUsuario);
//		return usuario;
//	}
//	
//	// Formulario para añadir tarjeta del Usuario
//	@PostMapping("/add-card/{id}")
//	public List<Card> altaTarjeta(@RequestBody Card tarjeta, @PathVariable(name="id") int  idUsuario) {
//		User user = userv.findById(idUsuario);
//		
//		user.addCard(tarjeta);
//		userv.register(user);
//		return user.getCards();
//	}
//	
//	// Borrar tarjeta del usuario
//	@GetMapping("/borrar-tarjeta/{id}")
//	public List<Card> eliminarTarjeta(Authentication aut, @PathVariable("id") int idTarjeta) {
//		String username = aut.getName();
//		User user = userv.findUserByEmail(username);
//		
//		Card tarjeta = cserv.findCardById(idTarjeta);
//		
//		user.removeCard(tarjeta);
//		userv.register(user);
//		
//		List<Card> tarjetas = cserv.cardsByUser(user.getIdUser());
//		return tarjetas;
//	}
//	
//	// Página para ditar una tarjeta
//	@PostMapping("/edit-card/{id}")
//	public int editCard(@RequestBody Card card, @PathVariable(name="id") int idCard) {
//		Authentication aut,
//		String username = aut.getName();
//		User user = userv.findUserByEmail(username);
//		if (cserv.findProductById(idCard) == null){
//			return 0;
//		}else{
//			card.setIdProduct(idCard);
//			if (cserv.modifyProduct(card) == 1) {
//				return 1;
//			}else {
//				return 0;
//			}
//	}
	
	// Formatear la fecha para el formulario
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

}

