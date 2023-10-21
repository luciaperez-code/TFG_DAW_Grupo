package com.edix.apirest.cinema.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userv;
	
//	@Autowired
//	private RolService rserv;
	
	// Página de inicio
	@GetMapping("/")
	public String inicioLogin(Authentication aut, HttpSession misesion) {

		return "inicio";
	}
	
	// Página se Sobre nosotros
	@GetMapping("/sobre-nosotros")
	public String sobreNosotros() {
		return "sobre-nosotros";
	}
	
	//Guardar el usuario en sesión después de loguearse
	@GetMapping("/index")
	public String procesarLogin(Authentication aut, Model model, HttpSession misesion) {
		String username = aut.getName();
		User user = userv.findUserByEmail(username);
		
		if (misesion.getAttribute("usuario") == null)
			misesion.setAttribute("usuario", user);
		
		return "redirect:/";
	}
	
//	// Mostrar lista Roles
//	@GetMapping("/lista-roles")
//	public String verRoles(Model model) {
//		List<Rol> lista = rserv.todosRoles();
//		model.addAttribute("listaRoles", lista);
//			
//		return "lista-roles";
//	}
	
	// Mostrar lista Usuarios
	@GetMapping("/lista-usuarios")
	public String verUsuarios(Model model) {
		List<User> lista = userv.allUsers();
		model.addAttribute("listaUsuarios", lista);
		
		return "lista-usuarios";
	}

	
}
