package org.udistrital.ojs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Estados")
public class Estado {
	
	private Integer id;
	private String estado;

	public Estado() {
	}

	public Estado(String estado) {
		this.estado = estado;
	}
	
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Column(name = "idEstado", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "Estado", nullable = false, length = 50)
	public String getEstado() {
		return estado;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
