package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * The persistent class for the items_in_order database table.
 * 
 */
@Entity
@Table(name="items_in_order")
@NamedQuery(name="ItemsInOrder.findAll", query="SELECT p FROM ItemsInOrder p")
public class ItemsInOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idItemsOrder;

	private int quantity;

	private BigDecimal price;
	
	//uni-directional many-to-one association to Order
	//@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@ManyToOne
	@JoinColumn(name="idOrder")
	private Order order;
	
	//uni-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="idProduct")
	private Product product;
	
	//uni-directional many-to-one association to Projection
	@ManyToOne
	@JoinColumn(name="idProjection")
	private Projection projection;

	public ItemsInOrder() {
	}


	public int getIdItemsOrder() {
		return idItemsOrder;
	}

	public void setIdItemsOrder(int idItemsOrder) {
		this.idItemsOrder = idItemsOrder;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}


	@Override
	public String toString() {
		return "ItemsInOrder [idItemsOrder=" + idItemsOrder + ", quantity=" + quantity + ", price=" + price + ", order="
				+ order + ", product=" + product + ", projection=" + projection + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idItemsOrder);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemsInOrder other = (ItemsInOrder) obj;
		return idItemsOrder == other.idItemsOrder;
	}
	
	

}