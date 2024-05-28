package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.entities.JSONResponse;

public interface AuthenticationService {
	
    String generateTokenForUser(String email, String password) throws Exception;
    JSONResponse authenticate(String email, String password) throws Exception;

}
