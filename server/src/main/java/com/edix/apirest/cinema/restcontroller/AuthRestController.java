package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.security.JwtRequest;
import com.edix.apirest.cinema.service.AuthenticationService;
import com.edix.apirest.cinema.utils.Utils;

@RestController
public class AuthRestController {

    @Autowired
    private AuthenticationService authService;


    @PostMapping("/login")
    public JSONResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JSONResponse response = new JSONResponse();
    	try{
    		response = authService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        }catch (Exception e){
        	Utils.createJSONResponseError(response, "authenticate", this.getClass().getSimpleName(), e);
        }
    	return response;
    }

	
}
