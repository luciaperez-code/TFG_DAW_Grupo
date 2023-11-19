package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * The persistent class for the screens database table.
 * 
 */
@Entity
@Table(name="screens")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQuery(name="Screen.findAll", query="SELECT p FROM Screen p")
public class Screen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idScreen;

	private String screenType;

	private String normalSeats;
	
	private String specialSeats;

	public Screen() {
	}

	public int getIdScreen() {
		return idScreen;
	}
	public void setIdScreen(int idScreen) {
		this.idScreen = idScreen;
	}

	public String getScreenType() {
		return screenType;
	}
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getNormalSeats() {
		return normalSeats;
	}
	public void setNormalSeats(String normalSeats) {
		this.normalSeats = normalSeats;
	}

	public String getSpecialSeats() {
		return specialSeats;
	}
	public void setSpecialSeats(String specialSeats) {
		this.specialSeats = specialSeats;
	}

	
	@Override
	public String toString() {
		return "Screen [idScreen=" + idScreen + ", screenType=" + screenType + ", normalSeats=" + normalSeats
				+ ", specialSeats=" + specialSeats + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idScreen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Screen other = (Screen) obj;
		return idScreen == other.idScreen;
	}

	

}