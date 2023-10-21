package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository urepo;

	// Lista de todos los usuarios
	@Override
	public List<User> allUsers() {
		return urepo.findAll();
	}

	// Buscar un usuario por su ID
	@Override
	public User findById(int idUsuario) {
		return urepo.findById(idUsuario).orElse(null);
	}

	// Buscar un usuario por su nombre
	@Override
	public User findUserByEmail(String username) {
		return urepo.buscarPorUsername(username);
	}

	// Registrar un usuario 
	@Override
	public boolean register(User usuario) {
		if (findUserByEmail(usuario.getEmail()) == null) {
			urepo.save(usuario);
			return true;
		}
		return false;
	}
	
	// Modificar un usuario
	@Override
	public int modificarUsuario(User usuario) {
		int filas = 0;
		User user = null;
		try {
			user = urepo.getOne(usuario.getIdUser());
			user = usuario;
			urepo.save(user);
			filas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

}
