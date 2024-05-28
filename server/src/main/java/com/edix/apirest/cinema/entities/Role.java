package com.edix.apirest.cinema.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private int idRole;

	private String name;

	public Role() {
	}

	public int getIdRol() {
		return idRole;
	}

	public void setIdRol(int idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return idRole == other.idRole;
	}
	
}