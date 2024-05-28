package com.edix.apirest.cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edix.apirest.cinema.entities.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Integer>{
	
	@Query("select s from Screen s where s.screenType like ?1")
	public List<Screen> findByScreenType(String type);
	
}
