
package com.edix.apirest.cinema.security;

import java.io.Serializable;
import java.util.Objects;

public class JwtResponse implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -1244944823037672950L;
	
	private final String jwttoken;

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

}
