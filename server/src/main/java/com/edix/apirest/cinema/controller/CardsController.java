package com.edix.apirest.cinema.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.apirest.cinema.entities.Card;
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
//	public String altaTarjeta(Model model, @PathVariable(name="id") int  idUsuario) {
//		User user = userv.findById(idUsuario);
//		model.addAttribute("usuario", user);
//		
//		return "alta-tarjeta";
//	}
//	
//	// Formulario para añadir tarjeta del Usuario
//	@PostMapping("/alta-tarjeta/{id}")
//	public String altaTarjeta(RedirectAttributes ratt, Card tarjeta, @PathVariable(name="id") int  idUsuario) {
//		User user = userv.findById(idUsuario);
//		
//		user.addCard(tarjeta);
//		userv.register(user);
//		
//		ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//				+ "  Tarjeta añadida correctamente\r\n"
//				+ "</div>");
//		
//		return "redirect:/lista-tarjetas/{id}";
//	}
//	
//	// Borrar tarjeta del usuario
//	@GetMapping("/borrar-tarjeta/{id}")
//	public List<Card> eliminarTarjeta(Authentication aut, Model model, @PathVariable("id") int idTarjeta) {
//		String username = aut.getName();
//		User user = userv.findUserByEmail(username);
//		
//		Card tarjeta = cserv.findCardById(idTarjeta);
//		
//		user.removeCard(tarjeta);
//		userv.register(user);
//		
//		List<Card> tarjetas = cserv.cardsByUser(user.getIdUser());
//		
//		model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//				+ "  Tarjeta eliminada correctamente\r\n"
//				+ "</div>");
//		
//		return tarjetas;
//	}
//	
//	// Página para ditar una tarjeta
//	@GetMapping("/editar-tarjeta/{id}")
//	public String enviarFormularioEditar(Model model, @PathVariable("id") int idTarjeta) {
//		model.addAttribute("tarjeta", cserv.findCardById(idTarjeta));
//		
//		return "editar-tarjeta";
//	}
//	
//	// Formulario para editar la tarjeta
//	@PostMapping("/editar-tarjeta/{id}")
//	public List<Card> editarTarjeta(Model model,Card tarjeta,  @PathVariable("id") int  idTarjeta) {
////		Authentication aut,
////		String username = aut.getName();
////		User user = userv.findUserByEmail(username);
//		
//		if (cserv.findCardById(idTarjeta) == null){
//			model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
//				+ "  La tarjeta no existe\r\n"
//				+ "</div>");
//		}else{
//			tarjeta.setIdCard(idTarjeta);
//			if (cserv.modifyCard(tarjeta) == 1) {
//				model.addAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
//					+ "  Tarjeta editada con éxito\r\n"
//					+ "</div>");
//			}else {
//				model.addAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
//					+ "  La tarjeta no se ha podido editar\r\n"
//					+ "</div>");
//			}
//		}
//		
//		List<Card> tarjetas = cserv.cardsByUser(user.getIdUser());
//		
//		return tarjetas;
//	}
	
	// Formatear la fecha para el formulario
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}

}

