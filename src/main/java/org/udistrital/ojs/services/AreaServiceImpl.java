package org.udistrital.ojs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.udistrital.ojs.daos.AreaDao;
import org.udistrital.ojs.entities.Area;

@Service("AreaService")
@Transactional
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> listar() {
		return areaDao.listar();
	}

	@Override
	public Area buscar(Integer id) {
		return areaDao.buscar(id);
	}

}
