package com.edix.apirest.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edix.apirest.cinema.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer>{

}
