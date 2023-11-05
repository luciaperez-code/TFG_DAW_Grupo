package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the screens database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQuery(name="Film.findAll", query="SELECT p FROM Film p")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFilm;

	private String name;

	private String genre;
	
	@Temporal(TemporalType.TIME)
	private Date duration;
	
	private String image;
	private String synopsis;

	public Film() {
	}

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	
	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", name=" + name + ", genre=" + genre + ", duration=" + duration + ", image="
				+ image + ", synopsis=" + synopsis + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFilm);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return idFilm == other.idFilm;
	}
	
}