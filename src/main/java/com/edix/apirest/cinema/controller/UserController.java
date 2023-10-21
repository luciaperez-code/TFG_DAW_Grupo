package com.edix.apirest.cinema.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.service.UserService;

@Controller
public class UserController {
	
//	@Autowired
//	private RolService rserv;
	
	@Autowired
	private UserService userv;
	
	
	@Autowired
	private CardService tserv;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Mostrar datos personales del Usuario
	@GetMapping("/datos-personales/{id}")
	public String datosUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		User user = userv.findById(idUsuario);
		model.addAttribute("datosUsuario", user);
				
		return "datos-personales";
	}
	
	// Ir a la página de registro
	@GetMapping("/registro")
	public String registrarUsuario(Model model) {
//		List<Rol> lista = rserv.todosRoles();
//		model.addAttribute("listaRoles", lista);
		
		return "registro";
	}
	
	// Formulario de registro de usuario
	@PostMapping("/registro")
	public String procRegistrarUsuario(User usuario, RedirectAttributes ratt) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setEnabled(1);
		
		usuario.setRegisterDate(new Date());
					
			if (userv.register(usuario)) {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  Usuario creado correctamente\r\n"
					+ "</div>");
				return "redirect:/index";
			}else {
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  Ha ocurrido un error, vuelve a intentarlo\r\n"
					+ "</div>");
				return "/registro";
			
		}
	
	}
	
	// Ir al formulario para datos persnales del Usuario
	@GetMapping("/editar-perfil/{id}")
	public String editarUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		User user = userv.findById(idUsuario);
		model.addAttribute("datosUsuario", user);
		
		return "editar-perfil";
	}
	
	// Formulario para editar los datos
	@PostMapping("/editar-perfil/{id}")
	public String procesarEdicionUsuario(RedirectAttributes ratt, User usuario, @PathVariable(name="id") int  idUsuario) {
		
		if(userv.findById(idUsuario) == null) {
			ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  El usuario no existe\r\n"
					+ "</div>");
		}else {
			User user = userv.findById(idUsuario);
			usuario.setIdUser(user.getIdUser());
			usuario.setEnabled(user.getEnabled());
			usuario.setBirthDate(user.getBirthDate());
			usuario.setRegisterDate(user.getRegisterDate());
			usuario.setCards(user.getCards());
			
			if (userv.modificarUsuario(usuario) == 1) {
				System.out.println("Usuario modificado con éxito :)");
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-success\" role=\"alert\">\r\n"
					+ "  El usuario se ha modificado con éxito\r\n"
					+ "</div>");
			}else {
				System.out.println("Usuario no modificado");
				ratt.addFlashAttribute("mensaje", "<div class=\"alert alert-warning\" role=\"alert\">\r\n"
					+ "  El usuario no se ha podido modificar\r\n"
					+ "</div>");
			}
		}
		
		return "redirect:/datos-personales/{id}";
	}
	
	
	// Mostrar lista de tarjetas por Usuario
	@GetMapping("/lista-tarjetas/{id}")
	public String tarjetasUsuario(Model model, @PathVariable(name="id") int  idUsuario) {
		List<Card> tarjetas = tserv.cardsByUser(idUsuario);
		User user = userv.findById(idUsuario);
			
		model.addAttribute("usuario", user);
		model.addAttribute("tarjetasUsuario", tarjetas);
						
		return "lista-tarjetas";
	}
	
	// Formatear la fecha de nacimiento
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
	
}
