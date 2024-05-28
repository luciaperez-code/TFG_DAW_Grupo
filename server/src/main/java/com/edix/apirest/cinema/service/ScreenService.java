package com.edix.apirest.cinema.service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Screen;

public interface ScreenService {
	
	JSONResponse findScreenById(int idScreen);
	JSONResponse findAll();
	JSONResponse findByScreenType(String type);
	
	JSONResponse insertScreen(Screen screen);
	JSONResponse deleteScreen(int idScreen);
	JSONResponse modifyScreen(Screen screen, int idScreen);

}
