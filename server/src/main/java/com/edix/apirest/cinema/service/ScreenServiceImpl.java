package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.ScreenRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenRepository srepo;

	@Override
	public JSONResponse findScreenById(int idScreen) {
		JSONResponse response = new JSONResponse();
		Screen screenById = srepo.getById(idScreen);
		if (screenById != null) {
			Utils.createJSONResponseOk(response, screenById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay salas con este ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse findAll() {
		JSONResponse response = new JSONResponse();
		List<Screen> allScreens = srepo.findAll();
		if (allScreens != null) {
			Utils.createJSONResponseOk(response, allScreens);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay salas :(");
		}
		return response;
	}

	@Override
	public JSONResponse findByScreenType(String type) {
		JSONResponse response = new JSONResponse();
		List<Screen> screensByType = srepo.findByScreenType(type);
		if (screensByType != null) {
			Utils.createJSONResponseOk(response, screensByType);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay salas de este tipo :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse insertScreen(Screen screen) {
		JSONResponse response = new JSONResponse();
		Utils.createJSONResponseOk(response, srepo.save(screen));
		return response;
	}
	
	@Override
	public JSONResponse deleteScreen(int idScreen) {
		JSONResponse response = new JSONResponse();
		Screen screenToDelete = srepo.findById(idScreen).orElse(null);
		if (screenToDelete != null) {
			srepo.delete(screenToDelete);
			Utils.createJSONResponseOk(response, "Sala borrada con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay salas con ese ID :(");
		}
		return response;
	}

	@Override
	public JSONResponse modifyScreen(Screen screen, int idScreen) {
		JSONResponse response = new JSONResponse();
		if (srepo.findById(idScreen) != null) {
			screen.setIdScreen(idScreen);
			Utils.createJSONResponseOk(response, srepo.save(screen));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay salas con ese ID :(");
		}
		return response;
	}
}
