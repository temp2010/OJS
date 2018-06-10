package org.udistrital.ojs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.EstadoDao;
import org.udistrital.ojs.entities.Estado;

@Service("EstadoService")
@Transactional
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoDao estadoDao;

	@Override
	public Estado buscar(Integer orden) {
		return estadoDao.buscar(orden);
	}

}
