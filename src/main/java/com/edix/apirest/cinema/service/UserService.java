package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.User;

public interface UserService {
	
	// Lista de todos los usuarios
	List<User> allUsers();
	
	// Buscar un usuario por su ID
	User findById(int idUsuario);
	
	// Buscar un usuario por su nombre
	User findUserByEmail(String email);
	
	// Registrar un usuario 
	boolean register(User usuario);
	
	// Modificar un usuario
	int modificarUsuario(User usuario);
	
	boolean updateUser(User usuario);
}
