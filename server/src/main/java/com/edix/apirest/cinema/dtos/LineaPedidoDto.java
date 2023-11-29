package com.edix.apirest.cinema.dtos;

import java.io.Serializable;
import java.util.Objects;

public class LineaPedidoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int idProduct;
	private int idProjection;
	private int quantity;
	private double price;
	private String occupiedNormalSeats;
	private String occupiedSpecialSeats;
	
	
	public int getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	public int getIdProjection() {
		return idProjection;
	}
	
	public void setIdProjection(int idProjection) {
		this.idProjection = idProjection;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
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

	@Override
	public String toString() {
		return "LineaPedidoDto [idProduct=" + idProduct + ", idProjection=" + idProjection + ", quantity=" + quantity
				+ ", price=" + price + ", occupiedNormalSeats=" + occupiedNormalSeats + ", occupiedSpecialSeats="
				+ occupiedSpecialSeats + "]";
	}	

}
