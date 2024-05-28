package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.dtos.UserRegisterDTO;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.User;

public interface UserService {
	
	JSONResponse allUsers();
	JSONResponse findById(int idUsuario);
	JSONResponse findUserByEmail(String email);
	User userByEmail(String email);
	
	JSONResponse register(UserRegisterDTO userDTO) throws Exception;
	JSONResponse deleteUser(int idUser);
	JSONResponse editUser(UserRegisterDTO usuario, int idUser);
	JSONResponse disableUser(int idUser);
}
