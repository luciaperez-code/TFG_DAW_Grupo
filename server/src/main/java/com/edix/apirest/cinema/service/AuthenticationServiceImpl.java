package com.edix.apirest.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.security.JwtTokenUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserServiceImpl userService;
    
    @Override
    public String generateTokenForUser(String email, String password) throws Exception {
        // Autenticar al usuario
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        // Si la autenticación tiene éxito, genero el token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtTokenUtil.generateToken(userDetails);
    }
    
    @Override
    public JSONResponse authenticate(String email, String password) throws Exception {
        JSONResponse response = new JSONResponse();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = this.generateTokenForUser(email, password);
            response.setCode(200);
            response.setSuccess(true);
            response.setToken(token);
            response.setSuccessResponse(userService.findUserByEmail(email));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        return response;
    }

}
