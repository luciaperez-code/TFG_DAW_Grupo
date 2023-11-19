package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.ScreenRepository;

@Service
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenRepository srepo;

	@Override
	public Screen findScreenById(int idScreen) {
		// TODO Auto-generated method stub
		return srepo.getById(idScreen);
	}

	@Override
	public List<Screen> findAll() {
		// TODO Auto-generated method stub
		return srepo.findAll();
	}

	@Override
	public List<Screen> find3Dscreens() {
		
		return srepo.findByScreenType("3D");
	}

	@Override
	public List<Screen> findByScreenType(String type) {
		return srepo.findByScreenType(type);
	}
	
	@Override
	public int insertScreen(Screen screen) {
		int filasInsertadas = 0;
		try {
			srepo.save(screen);
			filasInsertadas = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}

}
