package com.edix.apirest.cinema.dtos;

import java.io.Serializable;
import java.util.Objects;

public class LineaPedidoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int idProducto;
	private int idProyection;
	private int cantidad;
	private double precioVenta;
	
	
	public int getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	
	public int getIdProyection() {
		return idProyection;
	}
	
	public void setIdProyection(int idProyection) {
		this.idProyection = idProyection;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public double getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	@Override
	public String toString() {
		return "LineaPedidoDto [idProducto=" + idProducto + ", idProyection=" + idProyection + ", cantidad=" + cantidad
				+ ", precioVenta=" + precioVenta + "]";
	}
	
	

}
