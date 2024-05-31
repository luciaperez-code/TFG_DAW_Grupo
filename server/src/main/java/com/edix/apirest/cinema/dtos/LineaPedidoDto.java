package com.edix.apirest.cinema.dtos;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineaPedidoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Nullable
	private Integer idProduct;
    
    @Nullable
	private Integer idProjection;
    
    @Nullable
	private int quantity;
    
    @Nullable
	private double price;
    
    @Nullable
	private String occupiedNormalSeats;
    
    @Nullable
	private String occupiedSpecialSeats;
    
    @Nullable
    private int idCard;
		
	public LineaPedidoDto() {
	}

	public Integer getIdProduct() {
		return idProduct;
	}
	
	public void setIdProduct(@Nullable Integer idProduct) {
		this.idProduct = idProduct;
	}
	
	public Integer getIdProjection() {
		return idProjection;
	}
	
	public void setIdProjection(@Nullable Integer idProjection) {
		this.idProjection = idProjection;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(@Nullable int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(@Nullable double price) {
		this.price = price;
	}

	public String getOccupiedNormalSeats() {
		return occupiedNormalSeats;
	}

	public void setOccupiedNormalSeats(@Nullable String occupiedNormalSeats) {
		this.occupiedNormalSeats = occupiedNormalSeats;
	}

	public String getOccupiedSpecialSeats() {
		return occupiedSpecialSeats;
	}

	public void setOccupiedSpecialSeats(@Nullable String occupiedSpecialSeats) {
		this.occupiedSpecialSeats = occupiedSpecialSeats;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "LineaPedidoDto [idProduct=" + idProduct + ", idProjection=" + idProjection + ", quantity=" + quantity
				+ ", price=" + price + ", occupiedNormalSeats=" + occupiedNormalSeats + ", occupiedSpecialSeats="
				+ occupiedSpecialSeats + ", idCard=" + idCard + "]";
	}	
	
}
