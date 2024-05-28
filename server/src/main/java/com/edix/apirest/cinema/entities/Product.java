package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduct;

	private String name;
	private String image;

	private String description;
	

	private double price;

	private int stock;

	//uni-directional many-to-one association to productType
	@ManyToOne
	@JoinColumn(name="idProductType")
	private ProductType productType;
	
	//uni-directional many-to-one association to Film
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idRelatedFilm")
    @JsonIgnore
    private Film film;

	public Product() {
	}


	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public ProductType getProductType() {
		return (productType != null) ? productType : null;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	
	@Transient
    @JsonProperty("idFilm")
	public Integer getIdFilm() {
	    if (film != null) {
	        return film.getIdFilm();
	    } else {
	        return null;
	    }
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", name=" + name + ", image=" + image + ", description="
				+ description + ", price=" + price + ", stock=" + stock + ", productType=" + productType + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idProduct);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return idProduct == other.idProduct;
	}

	

}