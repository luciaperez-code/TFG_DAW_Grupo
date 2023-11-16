package com.edix.apirest.cinema.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.UserService;

@RestController
public class HomeController {
	
	@Autowired
	private UserService userv;
	
//	@Autowired
//	private RolService rserv;
		
	//Guardar el usuario en sesión después de loguearse
//	@GetMapping("/index")
//	public String procesarLogin(Authentication aut, Model model, HttpSession misesion) {
//		String username = aut.getName();
//		User user = userv.findUserByEmail(username);
//		
//		if (misesion.getAttribute("usuario") == null)
//			misesion.setAttribute("usuario", user);
//		
//		return "redirect:/";
//	}
		
}
