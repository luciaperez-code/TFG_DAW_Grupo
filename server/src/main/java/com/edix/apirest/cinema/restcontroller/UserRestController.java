package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.dtos.UserRegisterDTO;
import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.service.AuthenticationService;
import com.edix.apirest.cinema.service.UserService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/users")
@RestController
public class UserRestController {
	
//	@Autowired
//	private RolService rserv;
	
	@Autowired
	private UserService userv;

	@Autowired
	private AuthenticationService authService;
		
	@GetMapping("/all")
	public JSONResponse allUsers() {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.allUsers();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "findUserById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse findUserById(@PathVariable(name="id") int  idUsuario) {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.findById(idUsuario);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "findUserById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/email/{email}")
	public JSONResponse findUserByEmail(@PathVariable(name="email") String  email) {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.findUserByEmail(email);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "findUserByEmail", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/detail")
	public JSONResponse authenticatedUserDetail() {
		JSONResponse response = new JSONResponse();
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			response = userv.authenticatedUserInfo(authentication);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "findUserByEmail", this.getClass().getSimpleName(), e);
		}
		return response;
	}	
	
	@PostMapping("/register")
	public JSONResponse registerUser(@RequestBody UserRegisterDTO userDTO) {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.register(userDTO);
			if(response.getCode() == 200) {
				response.setToken(authService.generateTokenForUser(userDTO.getEmail(), userDTO.getPassword()));
			}
		}catch(Exception e) {
			Utils.createJSONResponseError(response, "registerUser",this.getClass().getSimpleName(), e);
			
		}
		return response;
	}
	
	@GetMapping("/delete-user/{id}")
	public JSONResponse deleteUser(@PathVariable(name="id") int idUser) {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.deleteUser(idUser);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteUser", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@PostMapping("/edit-user/{id}")
	public JSONResponse editUser(@RequestBody UserRegisterDTO userDTO, @PathVariable(name="id") int  idUser) {
		JSONResponse response = new JSONResponse();
		try {
			response = userv.editUser(userDTO, idUser);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteUser", this.getClass().getSimpleName(), e);
		}
		return response;		
	}
			
}
