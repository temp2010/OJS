package org.udistrital.ojs.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Roles")
public class Rol {
	
	private Integer id;
	private String rol;

	public Rol() {
	}

	public Rol(String rol) {
		this.rol = rol;
	}

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Column(name = "idRol", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "Rol", nullable = false, length = 50)
	public String getRol() {
		return rol;
	}
		
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
}
