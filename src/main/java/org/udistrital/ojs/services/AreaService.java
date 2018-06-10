package org.udistrital.ojs.services;

import java.util.List;

import org.udistrital.ojs.models.Area;

public interface AreaService {
	
	public List<Area> listar();

	public Area buscar(Integer id);
	
}
