package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.CardRepository;
import com.edix.apirest.cinema.repository.ScreenRepository;
import com.edix.apirest.cinema.repository.UserRepository;

@Service
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenRepository srepo;

	@Override
	public Screen findScreenById(int idScreen) {
		// TODO Auto-generated method stub
		return srepo.getById(idScreen);
	}
	
	

}
