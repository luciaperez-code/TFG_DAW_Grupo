package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the projections database table.
 * 
 */
@Entity
@Table(name="projections")
@NamedQuery(name="Projection.findAll", query="SELECT p FROM Projection p")
public class Projection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProjection;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date endDate;
	
	private Double price;

	private String occupiedNormalSeats;
	private String occupiedSpecialSeats;

	//uni-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="idFilm")
	private Film film;
	
	//uni-directional many-to-one association to Screen
	@ManyToOne
	@JoinColumn(name="idScreen")
	private Screen screen;

	public Projection() {
	}

	
	public int getIdProjection() {
		return idProjection;
	}

	public void setIdProjection(int idProjection) {
		this.idProjection = idProjection;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getOccupiedNormalSeats() {
		return occupiedNormalSeats;
	}

	public void setOccupiedNormalSeats(String occupiedNormalSeats) {
		this.occupiedNormalSeats = occupiedNormalSeats;
	}

	public String getOccupiedSpecialSeats() {
		return occupiedSpecialSeats;
	}

	public void setOccupiedSpecialSeats(String occupiedSpecialSeats) {
		this.occupiedSpecialSeats = occupiedSpecialSeats;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	
	@Override
	public String toString() {
		return "Projection [idProjection=" + idProjection + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", price=" + price + ", occupiedNormalSeats=" + occupiedNormalSeats + ", occupiedSpecialSeats="
				+ occupiedSpecialSeats + ", film=" + film + ", screen=" + screen + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProjection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projection other = (Projection) obj;
		return idProjection == other.idProjection;
	}

	

}