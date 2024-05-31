package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the screens database table.
 * 
 */
@Entity
@Table(name="films")
@NamedQuery(name="Film.findAll", query="SELECT p FROM Film p")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFilm;
	
	private String title;
	private int year;
	
	private String rated;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date released;
	
	private String runtime, genre, director, writer, actors, plot, language, country, awards;
	private double score;
	private boolean comingsoon;
	private String image;
	

	public Film() {
	}


	public int getIdFilm() {
		return idFilm;
	}


	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}


	public void setRated(String rated) {
		this.rated = rated;
	}


	public Date getReleased() {
		return released;
	}


	public void setReleased(Date released) {
		this.released = released;
	}


	public String getRuntime() {
		return runtime;
	}


	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}


	public String getPlot() {
		return plot;
	}


	public void setPlot(String plot) {
		this.plot = plot;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getAwards() {
		return awards;
	}


	public void setAwards(String awards) {
		this.awards = awards;
	}


	public double getScore() {
		return score;
	}


	public void setScore(double score) {
		this.score = score;
	}


	public boolean isComingsoon() {
		return comingsoon;
	}

	public void setComingsoon(boolean comingsoon) {
		this.comingsoon = comingsoon;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
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


	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
				+ released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
				+ writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
				+ ", awards=" + awards + ", score=" + score + ", comingsoon=" + comingsoon + ", images=" + image + "]";
	}
	
}