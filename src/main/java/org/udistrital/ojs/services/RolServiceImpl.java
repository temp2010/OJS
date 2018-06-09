package org.udistrital.ojs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.RolDao;
import org.udistrital.ojs.entities.Rol;

@Service("RolService")
@Transactional
public class RolServiceImpl implements RolService {
	
	@Autowired
	private RolDao rolDao;


	@Override
	public List<Rol> listar() {
		return rolDao.listar();
	}


	@Override
	public List<Rol> listarPublico() {
		return rolDao.listarPublico();
	}


	@Override
	public Rol Buscar(Integer id) {
		return rolDao.Buscar(id);
	}
	
}
