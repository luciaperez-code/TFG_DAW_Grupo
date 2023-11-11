package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


/**
 * The persistent class for the product_type database table.
 * 
 */
@Entity
@Table(name="product_type")
@NamedQuery(name="ProductType.findAll", query="SELECT pt FROM ProductType pt")
public class ProductType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProductType;

	private String name;


	public ProductType() {
	}
	
	public int getIdProductType() {
		return idProductType;
	}

	public void setIdProductType(int idProductType) {
		this.idProductType = idProductType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductType [idProductType=" + idProductType + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProductType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductType other = (ProductType) obj;
		return idProductType == other.idProductType;
	}

	
}