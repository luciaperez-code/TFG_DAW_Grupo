package com.edix.apirest.cinema.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.dtos.UserRegisterDTO;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Role;
import com.edix.apirest.cinema.entities.User;
import com.edix.apirest.cinema.repository.RoleRepository;
import com.edix.apirest.cinema.repository.UserRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository urepo;
	@Autowired
	private RoleRepository rrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public JSONResponse allUsers() {
		JSONResponse response = new JSONResponse();
		List<User> allUsers = urepo.findAll();
		if (allUsers != null) {
			Utils.createJSONResponseOk(response, allUsers);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay usuarios :(");
		}
		return response;
	}

	@Override
	public JSONResponse findById(int idUsuario) {
		JSONResponse response = new JSONResponse();
		User userById = urepo.findById(idUsuario).orElse(null);
		if (userById != null) {
			Utils.createJSONResponseOk(response, userById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay ningún user por ese ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse findUserByEmail(String email) {
		JSONResponse response = new JSONResponse();
		User userByEmail = urepo.findByEmail(email);
		if (userByEmail != null) {
			Utils.createJSONResponseOk(response, userByEmail);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay ningún user con ese email :(");
		}
		return response;
	}

	@Override
	public JSONResponse register(UserRegisterDTO userDTO) throws Exception{
		JSONResponse response = new JSONResponse();
        User user = new User();
		
        if (urepo.findByEmail(userDTO.getEmail()) == null){
	        user.setName(userDTO.getName());
			user.setSurname(userDTO.getSurname());
			user.setBirthDate(userDTO.getBirthDate());
			user.setEmail(userDTO.getEmail());
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			user.setEnabled(1);
			user.setRegisterDate(new Date());
			user.setCards(null);
            Role defaultRole = rrepo.findById(2).orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(defaultRole);
            User newUser = urepo.save(user);
			Utils.createJSONResponseOk(response, urepo.save(newUser));
        }else {
			Utils.createJSONResponseFailed(response, 404, "Ya existe un usuario con este email :(");
        }
		return response;
	}

	@Override
	public JSONResponse deleteUser(int idUser) {
		JSONResponse response = new JSONResponse();
		User userToDelete = urepo.findById(idUser).orElse(null);
		if (userToDelete != null) {
			urepo.delete(userToDelete);
			Utils.createJSONResponseOk(response, "Usuario borrado con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay usuarios con ese ID :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse editUser(UserRegisterDTO usuarioModificado, int idUser) {
		JSONResponse response = new JSONResponse();
		User userBD = urepo.findById(idUser).orElse(null);
		
		if (userBD != null) {
			userBD.setEmail(usuarioModificado.getEmail());
			userBD.setPassword(passwordEncoder.encode(usuarioModificado.getPassword()));
			userBD.setBirthDate(usuarioModificado.getBirthDate());
			userBD.setName(usuarioModificado.getName());
			userBD.setSurname(usuarioModificado.getSurname());
			Utils.createJSONResponseOk(response, urepo.save(userBD));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay usuarios con ese ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse disableUser(int idUser) {
		JSONResponse response = new JSONResponse();
		User usuario = urepo.findById(idUser).orElse(null);
		if (usuario != null) {
			usuario.setEnabled(0);
			Utils.createJSONResponseOk(response, urepo.save(usuario));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay usuarios con ese ID :(");
		}
		return response;	
	}

	@Override
	public User userByEmail(String email) {
		return urepo.findByEmail(email);
	}
	
}
