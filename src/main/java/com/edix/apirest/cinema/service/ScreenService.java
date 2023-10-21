package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Screen;

public interface ScreenService {
	
	// Buscar la tarjetas por su ID
	Screen findScreenById(int idScreen);
}
