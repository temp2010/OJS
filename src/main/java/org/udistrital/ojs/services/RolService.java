package org.udistrital.ojs.services;

import java.util.List;

import org.udistrital.ojs.entities.Rol;

public interface RolService {
	
	public List<Rol> listar();
	
	public List<Rol> listarPublico();
	
	public Rol Buscar(Integer id);

}
