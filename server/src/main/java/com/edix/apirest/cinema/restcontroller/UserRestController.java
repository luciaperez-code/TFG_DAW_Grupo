package com.edix.apirest.cinema.restcontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.service.CardService;
import com.edix.apirest.cinema.service.UserService;

@RequestMapping("/users")
@RestController
public class UserRestController {
	
//	@Autowired
//	private RolService rserv;
	
	@Autowired
	private UserService userv;
	
	
	@Autowired
	private CardService tserv;
		
	@GetMapping("/all")
	public List<User> allUsers() {
		List<User> users = userv.allUsers();
		return users;
	}
	
	// Mostrar datos personales del Usuario
	@GetMapping("/{id}")
	public User datosUsuario(@PathVariable(name="id") int  idUsuario) {
		User user = userv.findById(idUsuario);
		return user;
	}
	
	// Formulario de registro de usuario
	@PostMapping("/register")
	public int procRegistrarUsuario(@RequestBody User user) {
		user.setPassword(com.edix.apirest.cinema.entities.PasswordEncoder.encryptPassword(user.getPassword()));
		user.setEnabled(1);
		user.setRegisterDate(new Date());
					
		if (userv.register(user)) {
			return 1;
		}else {
			return 0;
		}
	}
		
	// Formulario para editar los datos
	@PostMapping("/edit-user/{id}")
	public int editUser(@RequestBody User usuario, @PathVariable(name="id") int  idUsuario) {
		
		if(userv.findById(idUsuario) == null) {
			return 0;
		}else {
			User user = userv.findById(idUsuario);
			usuario.setIdUser(user.getIdUser());
			usuario.setEnabled(user.getEnabled());
			usuario.setBirthDate(user.getBirthDate());
			usuario.setRegisterDate(user.getRegisterDate());
			usuario.setCards(user.getCards());
			
			if (userv.modificarUsuario(usuario) == 1) {
				System.out.println("Usuario modificado con éxito :)");
				return 1;
			}else {
				return 0;
			}
		}
		
	}
	
	// Mostrar lista de tarjetas por Usuario
	@GetMapping("/lista-tarjetas/{id}")
	public List<Card> tarjetasUsuario(@PathVariable(name="id") int  idUsuario) {
		List<Card> tarjetas = tserv.cardsByUser(idUsuario);						
		return tarjetas;
	}
	
//	// Mostrar lista de tarjetas por Usuario
//	@GetMapping("/login")
//	public boolean login(@RequestBody User user) {
//		List<Card> tarjetas = tserv.cardsByUser(idUsuario);						
//		return tarjetas;
//	}
	
	// Formatear la fecha de nacimiento
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
	}
		
}
