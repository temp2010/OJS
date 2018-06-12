package org.udistrital.ojs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reporte")
public class Reporte {
	
	private Integer id;
	private String rol;
	private String estado;
	private Integer cantidad;
	
	@Id
	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}
	
	@Column(name = "Rol")
	public String getRol() {
		return rol;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	
	@Column(name = "Cantidad")
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
