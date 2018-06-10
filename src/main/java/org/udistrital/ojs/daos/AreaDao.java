package org.udistrital.ojs.daos;

import java.util.List;

import org.udistrital.ojs.models.Area;

public interface AreaDao {
	
	public List<Area> listar();

	public Area buscar(Integer id);
	
}
