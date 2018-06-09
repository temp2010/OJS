package org.udistrital.ojs.daos;

import java.util.List;

import org.udistrital.ojs.entities.Area;
import org.udistrital.ojs.entities.Usuario;

public interface AreaDao {
	
	public List<Area> listar();

	public Area buscar(Integer id);
	
}
