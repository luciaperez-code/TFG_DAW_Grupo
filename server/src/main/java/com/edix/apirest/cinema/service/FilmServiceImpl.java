package com.edix.apirest.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Card;
import com.edix.apirest.cinema.entities.Film;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.CardRepository;
import com.edix.apirest.cinema.repository.FilmRepository;
import com.edix.apirest.cinema.repository.ScreenRepository;
import com.edix.apirest.cinema.repository.UserRepository;

@Service
public class FilmServiceImpl implements FilmService{

	@Autowired
	private FilmRepository frepo;

	@Override
	public Film findFilmById(int idFilm) {
		// TODO Auto-generated method stub
		return frepo.findById(idFilm).orElse(null);
	}

	@Override
	public Film findFilmByGenre(String genre) {
		// TODO Auto-generated method stub
		return frepo.findByGenre(genre);
	}

	@Override
	public List<Film> findFilmByTitle(String title) {
		// TODO Auto-generated method stub
		return frepo.findByTitle(title);
	}

	@Override
	public List<Film> findAll() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}

	@Override
	public List<Film> orderByScoreDesc() {
		// TODO Auto-generated method stub
		return frepo.orderByScoreDesc();
	}


}
