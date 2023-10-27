package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT p FROM Order p")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrder;

	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	private String status;

	//uni-directional many-to-one association to Card
	@ManyToOne
	@JoinColumn(name="idCard")
	private Card card;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;
	
	//bi-directional many-to-one association to ItemsInOrder
	@OneToMany(mappedBy="idItemsOrder", cascade= {CascadeType.PERSIST})
	private List<ItemsInOrder> itemsInOrder;
	
	
	public Order() {
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User idUser) {
		this.user = idUser;
	}

	public List<ItemsInOrder> getItemsInOrder() {
		return itemsInOrder;
	}

	public void setItemsInOrder(List<ItemsInOrder> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}



	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", createdDate=" + createdDate + ", status=" + status + ", card=" + card
				+ ", user=" + user + ", itemsInOrder=" + itemsInOrder + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(idOrder);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return idOrder == other.idOrder;
	}
	
	

}