package org.udistrital.ojs.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UsuariosRegistrados")
public class UsuarioRegistrado {

	private Integer usuario;
	private Estado estado;
	private Area area;
	private Date fecha;
	private String perfil;
	private String tematica;
	private String observacion;
	private Integer devoluciones;
	private Set<Soporte> soportes;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public UsuarioRegistrado() {
	}
	
	public UsuarioRegistrado(Integer usuario, Estado estado, Area area, String perfil) {
		this.usuario = usuario;
		this.estado = estado;
		this.area = area;
		this.fecha = new Date();
		this.perfil = perfil;
		this.devoluciones = 0;
	}
	
	public UsuarioRegistrado(Integer usuario, Estado estado, Area area, String perfil, String tematica) {
		this.usuario = usuario;
		this.estado = estado;
		this.area = area;
		this.fecha = new Date();
		this.perfil = perfil;
		this.tematica = tematica;
		this.devoluciones = 0;
	}
	
	@Id
	@Column(name = "idUsuario", nullable = false)
	public Integer getUsuario() {
		return usuario;
	}
	
	@OneToOne
	@JoinColumn(name = "idEstado")
	public Estado getEstado() {
		return estado;
	}
	
	@OneToOne
	@JoinColumn(name = "idArea")
	public Area getArea() {
		return area;
	}
	
	@Column(name = "FechaRegistro", nullable = false)
	public Date getFecha() {
		return fecha;
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "idUsuario")
	public Set<Soporte> getSoportes() {
		return this.soportes;
	}
	
	@Column(name = "Perfil", nullable = false)
	public String getPerfil() {
		return perfil;
	}
	
	@Column(name = "Tematicas", nullable = false)
	public String getTematica() {
		return tematica;
	}
	
	@Column(name = "Observacion", nullable = true)
	public String getObservacion() {
		return observacion;
	}
	
	@Column(name = "Devoluciones", nullable = true)
	public Integer getDevoluciones() {
		return devoluciones;
	}
	
	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void setArea(Area area) {
		this.area = area;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void setSoportes(Set<Soporte> soportes) {
		this.soportes = soportes;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public void setDevoluciones(Integer devoluciones) {
		this.devoluciones = devoluciones;
	}
	
}
