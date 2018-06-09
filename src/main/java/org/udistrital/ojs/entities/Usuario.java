package org.udistrital.ojs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	
	private Integer id;
	private Rol rol;
	private String nombre;
	private String correo;
	private String contrasena;
	private Boolean estado;
	private UsuarioRegistrado usuarioRegistrado;
	
	public Usuario() {
	}
	
	public Usuario(Rol rol, String nombre, String correo) {
		this.rol = rol;
		this.nombre = nombre;
		this.correo = correo;
		this.estado = true;
	}
	
	@Id
	@GenericGenerator(name = "native", strategy = "native")
	@GeneratedValue(generator = "native")
	@Column(name = "idUsuario", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	
	@OneToOne
	@JoinColumn(name = "idRol")
	public Rol getRol() {
		return rol;
	}
	
	@Column(name = "Nombre", nullable = false, length = 50)
	public String getNombre() {
		return nombre;
	}
	
	@Column(name = "Correo", nullable = false, length = 50)
	public String getCorreo() {
		return correo;
	}
	
	@Column(name = "Contrasena", nullable = false, length = 50)
	public String getContrasena() {
		return contrasena;
	}
	
	@Column(name = "estado", nullable = false)
	public boolean isEstado() {
		return estado;
	}
	
	@OneToOne
	@JoinColumn(name = "idUsuario")
	public UsuarioRegistrado getUsuarioRegistrado() {
		return usuarioRegistrado;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setUsuarioRegistrado(UsuarioRegistrado usuarioRegistrado) {
		this.usuarioRegistrado = usuarioRegistrado;
	}
	
}
