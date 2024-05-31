package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the cards database table.
 * 
 */
@Entity
@Table(name="cards")
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCard;

	private long number;
	private String holderName;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date expirationDate;
	
	private int cvv;

	public Card() {
	}

	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public long getNumber() {
		return number;
	}
	public void setNumber(long number) {
		this.number = number;
	}

	public String getHolderName() {
		return holderName;
	}
 	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public String toString() {
		return "Card [idCard=" + idCard + ", number=" + number + ", holderName=" + holderName + ", expirationDate="
				+ expirationDate + ", cvv=" + cvv + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCard);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return idCard == other.idCard;
	}

	
}