package org.udistrital.ojs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Soportes")
public class Soporte {

	private Integer id;
	private Integer usuario;
	private String soporte;
	
	public Soporte() {
	}
	
	public Soporte(Integer usuario, String soporte) {
		this.usuario = usuario;
		this.soporte = soporte;
	}
	
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Column(name = "idSoporte", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@Column(name = "idUsuario", unique = true, nullable = false)
	public Integer getIdUsuario() {
		return usuario;
	}
	
	@Column(name = "Soporte", nullable = false, length = 128)
	public String getSoporte() {
		return soporte;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}
	
	public void setIdUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
}
