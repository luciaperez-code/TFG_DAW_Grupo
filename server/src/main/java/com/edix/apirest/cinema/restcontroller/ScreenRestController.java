package com.edix.apirest.cinema.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.service.ScreenService;
import com.edix.apirest.cinema.utils.Utils;

@RequestMapping("/screens")
@RestController
public class ScreenRestController {
	
	@Autowired
	private ScreenService sserv;
	
	@GetMapping("/all")
	public JSONResponse getAllScreens() {
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.findAll();
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getAllScreens", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public JSONResponse getScreenById(@PathVariable("id") int idScreen) {
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.findScreenById(idScreen);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "getScreenById", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/type")
	public JSONResponse findScreenByType (@RequestParam("type") String type) {
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.findByScreenType("%" + type + "%");
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "findScreenByType", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@PostMapping("/add-screen")
	public JSONResponse addScreen(@RequestBody Screen screen) {
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.insertScreen(screen);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "addScreen", this.getClass().getSimpleName(), e);
		}
		return response;
	}
	
	@GetMapping("/delete-screen/{id}")
	public JSONResponse deleteScreen(@PathVariable(name="id") int  idScreen){
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.deleteScreen(idScreen);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "deleteScreen", this.getClass().getSimpleName(), e);
		}
		return response;
	}
		
	@PostMapping("/edit-screen/{id}")
	public JSONResponse editScreen(@RequestBody Screen screen, @PathVariable(name="id") int idScreen) {
		JSONResponse response = new JSONResponse();
		try {
			response = sserv.modifyScreen(screen, idScreen);
		} catch (Exception e) {
			Utils.createJSONResponseError(response, "editScreen", this.getClass().getSimpleName(), e);
		}
		return response;
		
	}
		
}
