package com.edix.apirest.cinema.service;

import java.util.List;

import com.edix.apirest.cinema.entities.Screen;

public interface ScreenService {
	
	Screen findScreenById(int idScreen);
	
	List<Screen> findAll();
	
	List<Screen> find3Dscreens();
	List<Screen> findByScreenType(String type);
	
}
