package org.udistrital.ojs.daos;

import java.util.List;

import org.udistrital.ojs.entities.Rol;

public interface RolDao {
	
	public List<Rol> listar();
	
	public List<Rol> listarPublico();
	
	public Rol Buscar(Integer id);

}
