package org.udistrital.ojs.models;

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
	private String tipo;

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
	
	@Column(name = "Rol", nullable = false, length = 15)
	public String getRol() {
		return rol;
	}
	
	@Column(name = "Tipo", nullable = false, length = 7)
	public String getTipo() {
		return tipo;
	}
		
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
